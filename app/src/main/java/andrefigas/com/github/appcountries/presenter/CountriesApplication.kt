package andrefigas.com.github.appcountries.presenter

import andrefigas.com.github.appcountries.modules
import android.app.Application
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

class CountriesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CountriesApplication)
            modules(listOf(modules))
        }
    }
}

