package andrefigas.com.github.appcountries.data

import andrefigas.com.github.appcountries.data.api.MyAtlanticoApi
import andrefigas.com.github.appcountries.data.api.Output
import andrefigas.com.github.appcountries.data.api.WikipediaApi
import andrefigas.com.github.appcountries.data.api.parseResponse
import andrefigas.com.github.appcountries.data.model.AreaResponse
import andrefigas.com.github.appcountries.data.model.WikipediaResponse
import andrefigas.com.github.appcountries.domain.model.Country

class RepositoryImpl(private val myAtlanticoApi: MyAtlanticoApi, private val wikipediaApi: WikipediaApi) :
    RepositoryContract {

    private fun AreaResponse.toCountry() = Country(this.iso3.lowercase(), this.areaName)

    private fun WikipediaResponse.toCountry(country: Country) = country.also {
        it.description = this.extract
    }

    override suspend fun getCountries(): List<Country> {
        return when (val response = myAtlanticoApi.getAreaCodes().parseResponse()) {
            is Output.Success -> response.value.map {
                it.toCountry()
            }
            is Output.Failure -> throw GetAreasError()
        }
    }

    override suspend fun getCountyDetail(country: Country): Country {
        return when (val response = wikipediaApi.getSummary(country.name).parseResponse()) {
            is Output.Success -> response.value.toCountry(country)
            is Output.Failure -> throw GetWikipediaError()
        }
    }


}

interface RepositoryContract {

    suspend fun getCountries(): List<Country>

    suspend fun getCountyDetail(country: Country): Country


}

class GetAreasError : Exception()

class GetWikipediaError : Exception()