package com.decadev.westwing.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
      val campaignsDataList = listofCampaigns[position]


      holder.bind(campaignsDataList, clickImage)

    }

    override fun getItemCount(): Int {
        return listofCampaigns.size
    }

    inner class WestWingViewHolder( val binding: WestwingCampaignViewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        var imagesView = itemView.findViewById<ImageView>(R.id.westwingImage)
//        var westWingMainScreenText = itemView.findViewById<TextView>(R.id.westWing_mainscreen_textView)

        fun bind(campaignList: Data, clickItem: ImageClickListener) {
            binding.westWingMainscreenTextView.text = campaignList.name
            Glide.with(context).load(campaignList.image.url).into(binding.westwingImage)

            binding.westwingImage.setOnClickListener {
                clickItem.imageClick(adapterPosition)
            }
        }

//        init {
//            westWingMainScreenText.text = listofCampaigns[adapterPosition].name
//            Glide.with(context).load(listofCampaigns[adapterPosition].image.url).into(imagesView)
//            itemView.setOnClickListener(this)
//        }

//        override fun onClick(v: View?) {
//            clickImage.imageClick(adapterPosition)
//        }


    }
}