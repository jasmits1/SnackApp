package com.example.snackapp.ui

import com.example.snackapp.model.SnackItem

interface SnackActivityView {
   fun setSnackList(snackList: List<SnackItem>)

}