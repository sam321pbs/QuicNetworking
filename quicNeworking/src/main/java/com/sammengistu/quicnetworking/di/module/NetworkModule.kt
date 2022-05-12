package com.sammengistu.quicnetworking.di.module

import com.sammengistu.quicnetworking.BuildConfig
import com.sammengistu.quicnetworking.data.source.finance.remote.retrofit.FinanceApiService
import com.sammengistu.quicnetworking.data.source.news.remote.retrofit.NewsApiService
import com.sammengistu.quicnetworking.data.source.weather.remote.retrofit.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService {
        val builder = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url()
            val url = originalHttpUrl.newBuilder().addQueryParameter(
                "apiKey", BuildConfig.NEWS_API_KEY
            ).build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(): WeatherApiService {
        val builder = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url()
            val url = originalHttpUrl.newBuilder().addQueryParameter(
                "apiKey", BuildConfig.WEATHER_API_KEY
            ).build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFinanceApiService(): FinanceApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://yfapi.net/")
            .addConverterFactory(GsonConverterFactory.create())
//            .client(builder.build())
            .build()
        return retrofit.create(FinanceApiService::class.java)
    }
}