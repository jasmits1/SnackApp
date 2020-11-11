package com.example.snackapp.presenter

import com.example.snackapp.model.SnackItem
import com.example.snackapp.model.SnackRepository
import com.example.snackapp.ui.SnackActivityView

class SnackPresenter(private var snackActivityView: SnackActivityView?, private val snackRepository: SnackRepository) {

    fun getSnackList() {
        snackActivityView?.setSnackList(snackRepository.getSnackList())
    }

    fun getCheckedSnacks(): List<SnackItem> {
        return(snackRepository.getCheckedSnackList())
    }

    fun addNewSnack(snack: SnackItem) {
        snackRepository.addNewSnack(snack)
    }

    fun submitOrder(order: List<SnackItem>) {
        snackRepository.submitOrder(order)
        getSnackList()
    }

    fun onCheckStatusChanged(adapterPosition: Int, isChecked: Boolean) {
        snackRepository.updateCheckStatus(adapterPosition, isChecked)
    }
}