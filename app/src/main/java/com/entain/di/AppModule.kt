package com.entain.di

import android.app.Application
import androidx.room.Room
import com.entain.data.datasource.LocalDataSourceImpl
import com.entain.data.datasource.NextRaceDataSourceImpl
import com.entain.data.datasource.local.RaceDatabase
import com.entain.data.remote.RaceApi
import com.entain.data.repository.NextRaceRepositoryImpl
import com.entain.domain.datasource.LocalDataSource
import com.entain.domain.datasource.NextRaceDataSource
import com.entain.domain.repository.NextRaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRaceDatabase(app: Application): RaceDatabase {
        return Room.databaseBuilder(
            app,
            RaceDatabase::class.java,
            RaceDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRaceApi(): RaceApi {
        return Retrofit.Builder()
            .baseUrl(RaceApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build()
            .create(RaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNextRaceRepository(
        nextRaceDataSource: NextRaceDataSource,
        localDataSource: LocalDataSource,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
        db: RaceDatabase
    ): NextRaceRepository {
        return NextRaceRepositoryImpl(
            nextRaceDataSource,
            localDataSource,
            db.categoryDao(),
            defaultDispatcher
        )
    }

    @Provides
    fun provideNextRaceDataSource(
        raceApi: RaceApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): NextRaceDataSource {
        return NextRaceDataSourceImpl(raceApi, ioDispatcher)
    }
    @Provides
    fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }
}