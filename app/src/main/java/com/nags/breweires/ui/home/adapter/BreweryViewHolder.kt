package com.nags.breweires.ui.home.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nags.breweries.R
import com.nags.breweires.business.home.model.BreweryDomainModel

/**
 * List item view holder.
 */
class BreweryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var mName: TextView = view.findViewById(R.id.name)
    private var mAddress: TextView = view.findViewById(R.id.address)
    private var mType: TextView = view.findViewById(R.id.type)


    fun bind(brewery: BreweryDomainModel) {
        mName.text = brewery.mName
        mAddress.text = brewery.getFormattedAddress()
        mType.text = itemView.context.getString(R.string.brewery_type, brewery.mType)
    }
}