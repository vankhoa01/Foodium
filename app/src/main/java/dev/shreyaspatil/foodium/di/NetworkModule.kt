package dev.shreyaspatil.foodium.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shreyaspatil.foodium.data.remote.api.FoodiumService
import dev.shreyaspatil.foodium.data.repository.PostsRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(FoodiumService.FOODIUM_API_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(FoodiumService::class.java)
    }
    single {
        PostsRepository(get(), get())
    }
}