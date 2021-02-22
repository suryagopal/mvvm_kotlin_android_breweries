package com.nags.breweires.business.common.dto

import com.nags.breweires.db.BreweryEntity
import com.google.gson.annotations.SerializedName

/**
 * Response model which represents a Brewery.
 */
open class Brewery() {

    @SerializedName("id")
    var mId: Int = -1

    @SerializedName("name")
    var mName: String? = null

    @SerializedName("brewery_type")
    var mType: String? = null

    @SerializedName("street")
    var mStreet: String? = null

    @SerializedName("city")
    var mCity: String? = null

    @SerializedName("state")
    var mState: String? = null

    @SerializedName("postal_code")
    var mPostalCode: String? = null

    @SerializedName("country")
    var mCountry: String? = null

    @SerializedName("longitude")
    var mLongitude: String? = null

    @SerializedName("latitude")
    var mLatitude: String? = null

    @SerializedName("phone")
    var mPhone: String? = null

    @SerializedName("website_url")
    var mUrl: String? = null

    @SerializedName("updated_at")
    var mUpdatedAt: String? = null

    constructor(localBrewery: BreweryEntity) : this() {
        mId = localBrewery.mId
        mName = localBrewery.mName
        mType = localBrewery.mType
        mStreet = localBrewery.mStreet
        mCity = localBrewery.mCity
        mState = localBrewery.mState
        mPostalCode = localBrewery.mPostalCode
        mCountry = localBrewery.mCountry
        mLongitude = localBrewery.mLongitude
        mLatitude = localBrewery.mLatitude
        mPhone = localBrewery.mPhone
        mUrl = localBrewery.mUrl
        mUpdatedAt = localBrewery.mUpdatedAt
    }

    companion object {
        fun fromLocal(localBreweries: List<BreweryEntity>): List<Brewery> {
            val list = mutableListOf<Brewery>()
            localBreweries.forEach {
                list.add(Brewery(it))
            }
            return list
        }
    }
}