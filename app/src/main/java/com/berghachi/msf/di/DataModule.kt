package com.berghachi.msf.di

import com.berghachi.msf.BuildConfig
import com.berghachi.msf.data.remote.ApiService
import com.berghachi.msf.data.remote.RemoteDataSource
import com.berghachi.msf.data.remote.RemoteDataSourceImpl
import com.berghachi.msf.data.remote.ResponseHandler
import com.berghachi.msf.data.repository.RepositoryImpl
import com.berghachi.msf.domain.repository.Repository
import com.berghachi.msf.domain.usecase.UsersUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {


    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideResponseHandler(): ResponseHandler = ResponseHandler()

    @Singleton
    @Provides
    fun providesRemoteService(gson: Gson): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun providesUseCase(repository: Repository): UsersUseCase {
        return UsersUseCase(repository)
    }



    @Singleton
    @Provides
    fun providesRepositoryDataSource(
        remoteDataSource: RemoteDataSource, responseHandler: ResponseHandler
    ): Repository {
        return RepositoryImpl(remoteDataSource, responseHandler)
    }

}