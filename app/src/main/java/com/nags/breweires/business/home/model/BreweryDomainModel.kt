package com.nags.breweires.business.home.model

import android.os.Parcel
import android.os.Parcelable
import com.nags.breweires.business.common.dto.Brewery
import com.nags.breweires.core.utils.StringUtils

/**
 * Model which is used to display data on the view.
 */
open class BreweryDomainModel : Parcelable {

    private var mId: Int = -1
    var mName: String
    var mType: String
    private var mStreet: String
    private var mCity: String
    private var mState: String
    private var mPostalCode: String
    private var mCountry: String
    var mLongitude: String
    var mLatitude: String
    var mPhone: String
    var mUrl: String
    private var mUpdatedAt: String

    constructor(brewery: Brewery) {
        mId = brewery.mId
        mName = StringUtils.emptyIfNull(brewery.mName)
        mType = StringUtils.emptyIfNull(brewery.mType)
        mStreet = StringUtils.emptyIfNull(brewery.mStreet)
        mCity = StringUtils.emptyIfNull(brewery.mCity)
        mState = StringUtils.emptyIfNull(brewery.mState)
        mPostalCode = StringUtils.emptyIfNull(brewery.mPostalCode)
        mCountry = StringUtils.emptyIfNull(brewery.mCountry)
        mLongitude = StringUtils.emptyIfNull(brewery.mLongitude)
        mLatitude = StringUtils.emptyIfNull(brewery.mLatitude)
        mPhone = StringUtils.emptyIfNull(brewery.mPhone)
        mUrl = StringUtils.emptyIfNull(brewery.mUrl)
        mUpdatedAt = StringUtils.emptyIfNull(brewery.mUpdatedAt)
    }

    constructor(parcel: Parcel) {
        mId = parcel.readInt()
        mName = StringUtils.emptyIfNull(parcel.readString())
        mType = StringUtils.emptyIfNull(parcel.readString())
        mStreet = StringUtils.emptyIfNull(parcel.readString())
        mCity = StringUtils.emptyIfNull(parcel.readString())
        mState = StringUtils.emptyIfNull(parcel.readString())
        mPostalCode = StringUtils.emptyIfNull(parcel.readString())
        mCountry = StringUtils.emptyIfNull(parcel.readString())
        mLongitude = StringUtils.emptyIfNull(parcel.readString())
        mLatitude = StringUtils.emptyIfNull(parcel.readString())
        mPhone = StringUtils.emptyIfNull(parcel.readString())
        mUrl = StringUtils.emptyIfNull(parcel.readString())
        mUpdatedAt = StringUtils.emptyIfNull(parcel.readString())
    }

    fun getFormattedAddress() =
        String.format("%s%s%s%s", mStreet, ", $mCity", ", $mState", ", $mCountry")

    companion object CREATOR : Parcelable.Creator<BreweryDomainModel> {
        override fun createFromParcel(parcel: Parcel): BreweryDomainModel {
            return BreweryDomainModel(parcel)
        }

        override fun newArray(size: Int): Array<BreweryDomainModel?> {
            return arrayOfNulls(size)
        }

        fun fromList(breweries: List<Brewery>): MutableList<BreweryDomainModel> {
            val list: MutableList<BreweryDomainModel> = ArrayList()
            breweries.forEach {
                list.add(BreweryDomainModel(it))
            }
            return list
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mId)
        parcel.writeString(mName)
        parcel.writeString(mType)
        parcel.writeString(mStreet)
        parcel.writeString(mCity)
        parcel.writeString(mState)
        parcel.writeString(mPostalCode)
        parcel.writeString(mCountry)
        parcel.writeString(mLongitude)
        parcel.writeString(mLatitude)
        parcel.writeString(mPhone)
        parcel.writeString(mUrl)
        parcel.writeString(mUpdatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

}