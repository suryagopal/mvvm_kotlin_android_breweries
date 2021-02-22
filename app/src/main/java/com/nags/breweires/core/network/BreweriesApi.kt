package com.nags.breweires.core.network

import com.nags.breweires.business.common.dto.Brewery
import io.reactivex.Single
import retrofit2.http.GET

/**
 * API class for application
 */
interface BreweriesApi {

    @GET("/breweries")
    fun getBreweries(): Single<List<Brewery>>

}