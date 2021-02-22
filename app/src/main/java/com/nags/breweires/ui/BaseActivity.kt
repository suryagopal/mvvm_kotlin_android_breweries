package com.nags.breweires.ui

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nags.breweries.R

/**
 * Base activity class to provide some handy methods for the activity.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Method to replace new fragment.
     */
    fun replaceFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction().replace(
            R.id.containerView,
            fragment,
            tag
        ).commit()
    }

    /**
     * Method to replace new fragment.
     */
    fun addFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction().add(
            R.id.containerView,
            fragment,
            tag
        ).addToBackStack(null).commit()
    }
}