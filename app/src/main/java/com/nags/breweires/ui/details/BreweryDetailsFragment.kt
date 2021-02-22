package com.nags.breweires.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.nags.breweries.R
import com.nags.breweires.core.utils.AppUtils
import com.nags.breweires.core.utils.StringUtils
import com.nags.breweires.ui.BaseFragment
import com.nags.breweires.business.home.model.BreweryDomainModel
import kotlinx.android.synthetic.main.fragment_brewery_details.*

/**
 * Details fragment, displayed when a list item is clicked.
 */
class BreweryDetailsFragment : BaseFragment() {

    companion object {
        private const val KEY_DATA = "KEY_DATA"

        fun getInstance(model: BreweryDomainModel): BreweryDetailsFragment {
            val fragment = BreweryDetailsFragment()
            fragment.arguments = Bundle().apply { putParcelable(KEY_DATA, model) }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_brewery_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val brewery = arguments?.getParcelable<BreweryDomainModel>(KEY_DATA)!!

        name.text = brewery.mName
        address.text = brewery.getFormattedAddress()
        type.text = view.context.getString(R.string.brewery_type, brewery.mType)
        img.setImageDrawable(ContextCompat.getDrawable(context!!, R.mipmap.ic_launcher))

        viewOnMap.setOnClickListener {
            if (StringUtils.isNotEmpty(brewery.mLatitude) && StringUtils.isNotEmpty(brewery.mLongitude)) {
                AppUtils.openMap(view.context, brewery.mLatitude, brewery.mLongitude)
            } else {
                showMessage()
            }
        }
        btnWebsite.setOnClickListener {
            if (StringUtils.isNotEmpty(brewery.mUrl)) {
                AppUtils.openUrl(view.context, brewery.mUrl)
            } else {
                showMessage()
            }
        }
        call.setOnClickListener {
            if (StringUtils.isNotEmpty(brewery.mPhone)) {
                AppUtils.call(view.context, brewery.mPhone)
            } else {
                showMessage()
            }
        }
    }

    private fun showMessage() {
        Toast.makeText(
            context,
            context?.getString(R.string.error_not_available),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onResume() {
        super.onResume()
        setupToolbar(R.string.frag_details_title, true)
    }

    override fun onDestroy() {
        super.onDestroy()
        setupToolbar(R.string.frag_breweries_title)
    }
}