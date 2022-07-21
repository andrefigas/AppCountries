package andrefigas.com.github.appcountries.domain

import andrefigas.com.github.appcountries.data.RepositoryContract
import andrefigas.com.github.appcountries.domain.model.Country


class GetCountries(private val repository: RepositoryContract) : GetCountriesUseCase {
    override suspend fun invoke(): List<Country> = repository.getCountries()
}

interface GetCountriesUseCase {

    suspend operator fun invoke(): List<Country>

}