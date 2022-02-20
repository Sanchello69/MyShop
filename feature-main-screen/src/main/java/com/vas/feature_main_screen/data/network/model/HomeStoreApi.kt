package com.vas.feature_main_screen.data.network.model

import android.app.Notification
import com.google.gson.annotations.SerializedName

data class HomeStoreApi (

    @SerializedName("id")
    val id: Int,

    @SerializedName("is_new")
    val isNew: Boolean,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("picture")
    val picture: String,

    @SerializedName("is_buy")
    val isBuy: String

        )