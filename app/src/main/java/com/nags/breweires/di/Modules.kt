package com.nags.breweires.di

import androidx.room.Room
import com.nags.breweires.business.home.HomeUseCase
import com.nags.breweires.business.home.repository.HomeLocalRepository
import com.nags.breweires.business.home.repository.HomeRepository
import com.nags.breweires.core.network.BreweriesApi
import com.nags.breweires.db.BreweriesDatabase
import com.nags.breweires.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * View model modules.
 */
val viewModelModule = module {
    viewModel {
        val homeUseCase =
            HomeUseCase(get(), get())
        HomeViewModel(homeUseCase)
    }
}

/**
 * Repository module.
 */
val repositoryModule = module {
    fun provideBreweriesApi(retrofit: Retrofit): BreweriesApi {
        return retrofit.create(BreweriesApi::class.java)
    }

    single {
        HomeRepository(
            provideBreweriesApi(
                get()
            )
        )
    }

    single {
        HomeLocalRepository(get())
    }
}

/**
 * Database module. i.e. Room
 */
val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            BreweriesDatabase::class.java,
            "breweries-db"
        ).build()
    }
}

/**
 * Retrofit module.
 */
val retrofitModule = module {
    fun getClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()
    }

    fun retrofit(client: OkHttpClient) = Retrofit
        .Builder()
        //TODO: Keeping it here intentionally, as I don't want project to throw sync error.
        // Usually we define in gradle properties and is not exposed in project.
        .baseUrl("https://api.openbrewerydb.org/")
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    single {
        retrofit(getClient())
    }
}
