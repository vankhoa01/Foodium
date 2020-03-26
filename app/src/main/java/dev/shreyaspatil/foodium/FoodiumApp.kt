package dev.shreyaspatil.foodium

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dev.shreyaspatil.foodium.di.databaseModule
import dev.shreyaspatil.foodium.di.networkModule
import dev.shreyaspatil.foodium.di.viewModelModule
import dev.shreyaspatil.foodium.utils.isNight
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodiumApp : Application() {


    override fun onCreate() {
        super.onCreate()

        initKoin()
        // Get UI mode and set
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(networkModule, databaseModule, viewModelModule)
        }
    }
}