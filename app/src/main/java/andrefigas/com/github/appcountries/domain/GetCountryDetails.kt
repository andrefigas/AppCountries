package andrefigas.com.github.appcountries.domain

import andrefigas.com.github.appcountries.data.RepositoryContract
import andrefigas.com.github.appcountries.domain.model.Country


class GetCountryDetails(private val repository: RepositoryContract) : GetCountryDetailsUseCase {
    override suspend fun invoke(country: Country): Country = repository.getCountyDetail(country)
}

interface GetCountryDetailsUseCase {

    suspend operator fun invoke(country: Country): Country

}