package arezou.ghorbani.s1_hilt.simple.di

import arezou.ghorbani.s1_hilt.NAMED_SITE_NAME
import arezou.ghorbani.s1_hilt.NAMED_USERNAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    @Named(NAMED_USERNAME)
    fun provideUserName(): String = "Mohammad Nouri"

    @Provides
    //@SiteName
    @Named(NAMED_SITE_NAME)
    fun provideSiteName(): String {
        return "Nouri.Academy"
    }
}