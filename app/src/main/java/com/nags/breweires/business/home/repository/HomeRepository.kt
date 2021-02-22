package com.nags.breweires.business.home.repository

import com.nags.breweires.core.network.BreweriesApi

/**
 * Repository class to get data from server.
 */
open class HomeRepository(private val api: BreweriesApi) {

    fun getBreweries() = api.getBreweries()

}