package com.nags.breweires.core.utils

import android.text.TextUtils

/**
 * Strings utility class.
 */
object StringUtils {

    private const val empty = ""

    /**
     * Check if string is empty return empty string otherwise return string.
     * @param string String value to be checked
     * @return String value
     */
    fun emptyIfNull(string: String?) = if (TextUtils.isEmpty(string)) empty else string!!

    /**
     * Check is a string is not empty.
     * @param string String value to be checked.
     * @return Results
     */
    fun isNotEmpty(string: String) = string.trim().isNotEmpty()

}