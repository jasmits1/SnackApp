package com.example.snackapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * This serves as the model for a SnackItem.
 *
 */
@Parcelize
data class SnackItem(
    @SerializedName("name") var name: String,
    @SerializedName("isVeg") var isVeg: Boolean,
    @SerializedName("isChecked") var isChecked: Boolean = false
) : Parcelable