package com.sammengistu.quicnetworking.di.module

import com.sammengistu.quicnetworking.data.source.finance.remote.FinanceRemoteDataSource
import com.sammengistu.quicnetworking.data.source.finance.remote.FinanceRemoteDataSourceImpl
import com.sammengistu.quicnetworking.data.source.news.remote.NewsRemoteDataSource
import com.sammengistu.quicnetworking.data.source.news.remote.NewsRemoteDataSourceImpl
import com.sammengistu.quicnetworking.data.source.weather.remote.WeatherRemoteDataSource
import com.sammengistu.quicnetworking.data.source.weather.remote.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindNewsRemoteDataSource(remoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    abstract fun bindWeatherRemoteDataSource(remoteDataSourceImpl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @Binds
    abstract fun bindFinanceRemoteDataSource(remoteDataSourceImpl: FinanceRemoteDataSourceImpl): FinanceRemoteDataSource
}