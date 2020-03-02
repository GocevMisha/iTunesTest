package com.gocdev.itunestestapp.di.modules

import android.app.Application
import androidx.room.Room
import com.gocdev.itunestestapp.api.ITunesService
import com.gocdev.itunestestapp.db.AlbumDao
import com.gocdev.itunestestapp.db.SongDao
import com.gocdev.itunestestapp.db.ITunesDb
import com.gocdev.itunestestapp.util.LiveDataCallAdapterFactory
import com.gocdev.itunestestapp.vo.Album
import com.gocdev.itunestestapp.vo.ITunesObject
import com.gocdev.itunestestapp.vo.Song
import com.gocdev.itunestestapp.vo.WrapperType
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideSearchService(): ITunesService {
        return Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ITunesService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(ITunesObject::class.java, "wrapperType")
                    .withSubtype(Song::class.java, WrapperType.track.name)
                    .withSubtype(Album::class.java, WrapperType.collection.name)
            )
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    @Singleton
    @Provides
    fun provideDb(app: Application): ITunesDb {
        return Room
            .databaseBuilder(app, ITunesDb::class.java, "itunes.db")
            .fallbackToDestructiveMigration()
            .build()
    }



    @Singleton
    @Provides
    fun provideAlbumDao(db: ITunesDb): AlbumDao {
        return db.albumDao()
    }

    @Singleton
    @Provides
    fun provideSongDao(db: ITunesDb): SongDao {
        return db.songDao()
    }

}