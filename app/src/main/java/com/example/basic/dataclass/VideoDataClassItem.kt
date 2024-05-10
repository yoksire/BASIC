package com.example.basic.dataclass

import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName



@Serializable
data class VideoDataClassItem(
    @SerializedName("channel_name")
    var channelName: String?=null,
    @SerializedName("date_added")
    var dateAdded: String?=null,
    @SerializedName("description")
    var description: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("likes")
    var likes: Int,
    @SerializedName("thumbnail")
    var thumbnail: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("videoURL")
    var videoURL: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("views")
    var views: String
)
