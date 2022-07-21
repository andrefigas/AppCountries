package andrefigas.com.github.appcountries.presenter.details

import andrefigas.com.github.appcountries.data.GetWikipediaError
import andrefigas.com.github.appcountries.domain.GetCountryDetailsUseCase
import andrefigas.com.github.appcountries.domain.model.Country
import andrefigas.com.github.appcountries.presenter.BaseViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountryDetailsViewModel(val getCountryDetailsUseCase: GetCountryDetailsUseCase) :
    BaseViewModel<Country>() {

    override val data = MutableLiveData<Country>()

    fun getCountryDetails(ct: Country) {
        observer.onStart()
        viewModelScope.launch {
            try {
                data.value = getCountryDetailsUseCase(ct)
                println(data.value)
            } catch (e: GetWikipediaError) {
                observer.onError()
            }

        }
        observer.onFinish()
    }

}