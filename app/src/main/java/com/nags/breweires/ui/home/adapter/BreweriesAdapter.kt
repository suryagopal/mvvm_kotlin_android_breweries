package com.nags.breweires.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nags.breweires.business.home.model.BreweryDomainModel
import com.nags.breweries.R

/**
 * Adapter class for breweries items.
 */
class BreweriesAdapter: RecyclerView.Adapter<BreweryViewHolder>() {

    private var mData: List<BreweryDomainModel>? = null

    fun setData(data: List<BreweryDomainModel>) {
        mData = data
        notifyDataSetChanged()
    }

    /**
     * Callback interface for list.
     */
    interface IItemCallback {
        fun onBrewerySelected(brewery: BreweryDomainModel)
    }

    private var mCallback: IItemCallback? = null

    fun setItemClickListener(callback: IItemCallback) {
        mCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_brewery_view, parent, false)
        return BreweryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mData != null) mData!!.size else 0
    }

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {
        val brewery = mData!![position]
        holder.bind(brewery)
        holder.itemView.setOnClickListener {
            mCallback?.onBrewerySelected(brewery)
        }
    }
}
