package andrefigas.com.github.appcountries.presenter

import androidx.lifecycle.Observer

interface BaseObserver<T> : Observer<T> {

    fun onStart()

    fun onFinish()

    fun onError()

}