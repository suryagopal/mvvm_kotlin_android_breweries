package com.nags.breweires.business.home.repository

import com.nags.breweires.db.BreweriesDatabase
import com.nags.breweires.db.BreweryEntity

class HomeLocalRepository(private val database: BreweriesDatabase) {

    fun insertAll(breweryEntity: List<BreweryEntity>) {
        database.breweryDao().deleteAll()
        database.breweryDao().insertBreweries(breweryEntity)
    }

    fun getLocalBreweries() = database.breweryDao().getAll()
}