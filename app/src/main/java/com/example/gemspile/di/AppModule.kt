package com.example.gemspile.di

import android.content.Context
import androidx.room.Room
import com.example.gemspile.data.repository.VideoRepositoryImpl
import com.example.gemspile.local_source.AppDatabase
import com.example.gemspile.local_source.dao.VideoDao
import com.example.gemspile.storage_interface.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDB(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "app_database"
        ).build()
    }

    @Provides
    fun provideVideoDao(appDatabase: AppDatabase): VideoDao {
        return appDatabase.videoDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {
    @Singleton
    @Binds
    abstract fun bindVideoRepository(
        videoRepositoryImpl: VideoRepositoryImpl
    ): VideoRepository
}