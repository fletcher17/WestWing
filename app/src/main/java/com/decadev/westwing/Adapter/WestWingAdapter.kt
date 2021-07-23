package com.decadev.westwing.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decadev.westwing.DataModel.Data
import com.decadev.westwing.R
import com.decadev.westwing.clickListener.ImageClickListener
import com.decadev.westwing.databinding.WestwingCampaignViewsBinding

class WestWingAdapter(
    var context: Context,
    var listofCampaigns: List<Data>,
    var clickImage: ImageClickListener
) : RecyclerView.Adapter<WestWingAdapter.WestWingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WestWingViewHolder {
        val binding =
            WestwingCampaignViewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WestWingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WestWingViewHolder, position: Int) {

        Glide.with(context).load(listofCampaigns[position].image.url).into(holder.imagesView)
    }

    override fun getItemCount(): Int {
        return listofCampaigns.size
    }

    inner class WestWingViewHolder(binding: WestwingCampaignViewsBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var imagesView = itemView.findViewById<ImageView>(R.id.westwingImage)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickImage.imageClick(adapterPosition)
        }


    }
}