package com.rcflechas.shoppingcartapp.app.di

import android.content.Context
import androidx.room.Room
import com.rcflechas.shoppingcartapp.models.data.local.DataBase
import com.rcflechas.shoppingcartapp.models.data.local.dao.CartDAO
import com.rcflechas.shoppingcartapp.models.data.local.dao.MovieDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            "database_movies"
        ).build()
    }

    @Provides
    fun provideMovieDAO(dataBase: DataBase): MovieDAO {
        return dataBase.movieDAO()
    }

    @Provides
    fun provideCartDAO(dataBase: DataBase): CartDAO {
        return dataBase.cartDAO()
    }
}