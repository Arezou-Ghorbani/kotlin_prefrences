package arezou.ghorbani.s1_koin

import arezou.ghorbani.s1_koin.scopes.personModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinReflectAPI
import org.koin.core.context.startKoin

@KoinReflectAPI
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //Koin
        startKoin {
            //Context
            androidContext(this@MyApp)
            //Simple
            //modules(userModule)

            //Interface
            //modules(bmwModule)

            //Qualifiers
            //modules(humanModule)

            //ViewModel
            //modules(viewModelModule)

            //Room
            //modules(databaseModule, repositoryModule, roomViewModelModule)
            //modules(databaseModule)

            //Retrofit
            //modules(networkModule, moviesModule)

            //Scope
            modules(personModule)
        }
    }
}