package com.a.yelpgraphql.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.a.yelpgraphql.R
import com.a.yelpgraphql.databinding.ItemBusinessBinding
import com.a.yelpgraphql.domain.models.BusinessModel
import com.a.yelpgraphql.domain.models.BusinessSearchResponseModel

class BusinessesAdapter(
    private val businessesList: List<BusinessModel>,
    private val clickAction: (BusinessModel) -> Unit
) : RecyclerView.Adapter<BusinessesAdapter.ViewHolder>() {


    inner class ViewHolder(private val view: ItemBusinessBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            view.root.setOnClickListener { clickAction(businessesList[adapterPosition]) }
        }

        fun bindTo(businessModel: BusinessModel) {
            with(view) {
                businessModel.apply {
                    tvRestaurantName.text = name
                    ivRestaurant.load(photos[0].photo) {
                        crossfade(true)
                        placeholder(R.drawable.ic_launcher_foreground)
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBusinessBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(businessesList[position])
    }

    override fun getItemCount(): Int = businessesList.size


}