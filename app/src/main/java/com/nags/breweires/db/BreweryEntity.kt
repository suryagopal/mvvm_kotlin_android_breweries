package com.nags.breweires.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breweries")
class BreweryEntity(
    @PrimaryKey @ColumnInfo(name = "id") val mId: Int,
    @ColumnInfo(name = "name") var mName: String?,
    @ColumnInfo(name = "brewery_type") var mType: String?,
    @ColumnInfo(name = "street") var mStreet: String?,
    @ColumnInfo(name = "city") var mCity: String?,
    @ColumnInfo(name = "state") var mState: String?,
    @ColumnInfo(name = "postal_code") var mPostalCode: String?,
    @ColumnInfo(name = "country") var mCountry: String?,
    @ColumnInfo(name = "longitude") var mLongitude: String?,
    @ColumnInfo(name = "latitude") var mLatitude: String?,
    @ColumnInfo(name = "phone") var mPhone: String?,
    @ColumnInfo(name = "website_url") var mUrl: String?,
    @ColumnInfo(name = "updated_at") var mUpdatedAt: String?
)