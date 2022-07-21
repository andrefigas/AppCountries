package andrefigas.com.github.appcountries

import andrefigas.com.github.appcountries.data.RepositoryContract
import andrefigas.com.github.appcountries.data.RepositoryImpl
import andrefigas.com.github.appcountries.data.api.ApiProvider
import andrefigas.com.github.appcountries.data.api.MyAtlanticoApi
import andrefigas.com.github.appcountries.data.api.WikipediaApi
import andrefigas.com.github.appcountries.domain.GetCountries
import andrefigas.com.github.appcountries.domain.GetCountriesUseCase
import andrefigas.com.github.appcountries.domain.GetCountryDetails
import andrefigas.com.github.appcountries.domain.GetCountryDetailsUseCase
import andrefigas.com.github.appcountries.presenter.countries.CountriesViewModel
import andrefigas.com.github.appcountries.presenter.details.CountryDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single {
        ApiProvider().createService(MyAtlanticoApi::class.java)
    }

    single {
        ApiProvider().createService(WikipediaApi::class.java)
    }

    single<RepositoryContract>{
        RepositoryImpl(get(), get())
    }

    single<GetCountriesUseCase> {
        GetCountries(get())
    }

    single<GetCountryDetailsUseCase> {
        GetCountryDetails(get())
    }

    viewModel {
        CountriesViewModel(get())
    }

    viewModel {
        CountryDetailsViewModel(get())
    }

}