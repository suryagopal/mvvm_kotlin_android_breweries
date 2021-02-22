package com.nags.breweires.core.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri


/**
 * Utility class which provides some handy functions.
 */
object AppUtils {
    /**
     * Open location on google maps application.
     * @param context Context
     * @param lat Latitude
     * @param lon Longitude
     */
    fun openMap(context: Context, lat: String, lon: String) {
        val uri = Uri.parse("geo:%s,%s".format(lat, lon))
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
        }
    }

    /**
     * Dial a number.
     * @param context Context
     * @param phone Phone number
     */
    fun call(context: Context, phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    /**
     * Open a URL in browser.
     * @param context Context
     * @param url URL to be opened.
     */
    fun openUrl(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (browserIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(browserIntent)
        }
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}