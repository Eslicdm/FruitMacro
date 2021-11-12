package com.eslirodrigues.fruitmacro.di

import com.eslirodrigues.fruitmacro.data.retrofit.FruitApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): FruitApi {
        return Retrofit.Builder()
            .run {
                baseUrl("https://www.fruityvice.com/")
                addConverterFactory(MoshiConverterFactory.create(moshi))
                build()
            }.create(FruitApi::class.java)
    }

}