package com.dicoding.myrecyclerview

import android.media.Rating
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Hero( val name: String, val description: String, val photo: String, val rating: String) : Parcelable