package com.sammengistu.quicnetworking.di.module

import com.sammengistu.quicnetworking.data.source.finance.repository.FinanceRepository
import com.sammengistu.quicnetworking.data.source.weather.repository.WeatherRepository
import com.sammengistu.quicnetworking.data.source.weather.repository.WeatherRepositoryImpl
import com.sammengistu.quicnetworking.data.source.finance.repository.FinanceRepositoryImpl
import com.sammengistu.quicnetworking.data.source.news.repository.NewsRepository
import com.sammengistu.quicnetworking.data.source.news.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(repo: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindWeatherRepository(repo: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindFinanceRepository(repo: FinanceRepositoryImpl): FinanceRepository
}