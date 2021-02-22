package com.nags.breweires.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BreweryDao {
    @Query("SELECT * FROM breweries")
    fun getAll(): List<BreweryEntity>

    @Insert
    fun insertBreweries(breweries: List<BreweryEntity>)

    @Query("DELETE FROM breweries")
    fun deleteAll()
}