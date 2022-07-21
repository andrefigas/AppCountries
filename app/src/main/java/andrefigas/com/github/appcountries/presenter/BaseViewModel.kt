package andrefigas.com.github.appcountries.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel(){

    protected abstract val data : MutableLiveData<T>
    lateinit var observer : BaseObserver<T>

    fun observe(lifecycleOwner: LifecycleOwner, presenterObserver: BaseObserver<T>){
        this.observer = presenterObserver
        data.observe(lifecycleOwner, presenterObserver)
    }

}