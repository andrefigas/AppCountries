package andrefigas.com.github.appcountries.presenter.countries

import andrefigas.com.github.appcountries.data.GetAreasError
import andrefigas.com.github.appcountries.domain.GetCountriesUseCase
import andrefigas.com.github.appcountries.domain.model.Country
import andrefigas.com.github.appcountries.presenter.BaseViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountriesViewModel(private val getCountriesUseCase: GetCountriesUseCase) :
    BaseViewModel<List<Country>>() {

    override val data = MutableLiveData<List<Country>>()

    fun getCountries() {
        observer.onStart()
        viewModelScope.launch {
            try {
                data.value = getCountriesUseCase()
            } catch (e: GetAreasError) {
                observer.onError()
            }
        }
        observer.onFinish()
    }

}