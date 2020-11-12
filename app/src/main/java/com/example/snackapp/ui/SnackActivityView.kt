package com.example.snackapp.ui

import com.example.snackapp.model.SnackItem

/**
 * This serves as a contract between the Presenter and the Activity.
 */
interface SnackActivityView {
   fun setSnackList(snackList: List<SnackItem>)
}