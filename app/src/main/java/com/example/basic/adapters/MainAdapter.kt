package com.example.basic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.basic.dataclass.VideoDataClassItem
import com.example.basic.databinding.VideoItemBinding

class MainAdapter(private val videoList:List<VideoDataClassItem>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var filterList :List<VideoDataClassItem> = videoList

    fun filter(text: String) {
        filterList = if (text.isEmpty()) {
            videoList
        } else {
            videoList.filter { it.title.contains(text, true) }
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(VideoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val video = filterList[position]
        holder.bind(video)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }
    class MainViewHolder(val binding : VideoItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: VideoDataClassItem) {
            binding.tvTitle.text = video.title
            binding.tvDescription.text = video.description
            binding.tvVideoTime.text = video.date
            binding.tvLikes.text = "${video.likes} Likes"
            binding.tvViews.text = "${video.views} Views"

            Glide.with(binding.root.context)
                .load(video.thumbnail)
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .into(binding.ivThumbnail)
        }


    }
}