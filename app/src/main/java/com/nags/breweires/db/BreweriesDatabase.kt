package com.nags.breweires.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BreweryEntity::class], version = 1)
abstract class BreweriesDatabase : RoomDatabase() {
    abstract fun breweryDao(): BreweryDao
}