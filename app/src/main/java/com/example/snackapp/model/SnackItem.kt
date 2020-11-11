package com.example.snackapp.model

import com.google.gson.annotations.SerializedName

data class SnackItem(
    @SerializedName("name") var name: String,
    @SerializedName("isVeg") var isVeg: Boolean,
                                    var isChecked: Boolean = false
)