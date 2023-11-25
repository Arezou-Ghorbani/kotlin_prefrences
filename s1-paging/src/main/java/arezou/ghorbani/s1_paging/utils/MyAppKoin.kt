package arezou.ghorbani.s1_paging.utils

import arezou.ghorbani.s1_paging.di.moviesModule
import android.app.Application
import org.koin.core.context.startKoin

class MyAppKoin : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(moviesModule)
        }
    }
}