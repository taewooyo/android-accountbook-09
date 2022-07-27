package com.woowa.accountbook.di

import android.content.Context
import com.woowa.accountbook.data.local.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: ApplicationContext): DatabaseHelper {
        return DatabaseHelper(context = context as Context)
    }
}