package com.example.snackapp.presenter

import com.example.snackapp.model.SnackItem
import com.example.snackapp.model.SnackRepository
import com.example.snackapp.ui.SnackActivityView

/**
 * The presenter serves as the glue between the UI and the repository.
 *
 * This is another class that seems like overkill in an app such as this but I wanted
 * to demonstrate a real architecture.
 */
class SnackPresenter(
    private var snackActivityView: SnackActivityView?,
    private val snackRepository: SnackRepository
) {

    /**
     * Retrieves the current list of snacks from the repository, and by
     * extension the database or network if available.
     */
    fun getSnackList() {
        snackActivityView?.setSnackList(snackRepository.getSnackList())
    }


    fun getCheckedSnacks(): List<SnackItem> {
        return (snackRepository.getCheckedSnackList())
    }

    fun addNewSnack(snack: SnackItem) {
        snackRepository.addNewSnack(snack)
    }

    fun submitOrder(order: List<SnackItem>) {
        snackRepository.submitOrder(order)
        getSnackList()
    }

    fun updateSnackList(list: List<SnackItem>) {
        snackRepository.setSnackList(list)
    }

    fun onCheckStatusChanged(snackItem: SnackItem, isChecked: Boolean) {
        snackRepository.updateCheckStatus(snackItem, isChecked)
    }
}