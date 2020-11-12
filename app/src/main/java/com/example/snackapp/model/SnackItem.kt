package com.example.snackapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SnackItem(
    @SerializedName("name") var name: String,
    @SerializedName("isVeg") var isVeg: Boolean,
                                    var isChecked: Boolean = false
) : Parcelable