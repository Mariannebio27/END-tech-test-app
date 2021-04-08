package com.mariannecunha.data.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.mariannecunha.data.ProductRepositoryImpl
import com.mariannecunha.data.remote.ProductService
import com.mariannecunha.domain.repository.ProductRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    factory {
        ProductRepositoryImpl(
            get<ProductService>()
        ) as ProductRepository
    }

    factory {
        createProductService(
            get<Retrofit>()
        )
    }

    single {
        createRetrofit(
            get<OkHttpClient>()
        )
    }

    factory {
        createOkHttpClient()
    }

    single {
        createSharedPreferences(androidContext())
    }
}

private fun createProductService(retrofit: Retrofit): ProductService {
    return retrofit.create(ProductService::class.java)
}

private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl("https://www.endclothing.com/media/catalog/")
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkHttpClient(): OkHttpClient {
    val timeoutInSeconds = 10L
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

    return OkHttpClient.Builder()
        .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

private fun createSharedPreferences(context: Context) =
    PreferenceManager.getDefaultSharedPreferences(context)
