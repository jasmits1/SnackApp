package com.example.snackapp.model

import kotlin.time.milliseconds

/**
 * The SnackRepository class's job is to manage how data is transferred to and
 * from the local database and remote data-store depending on predefined conditions
 * such as network availability.
 *
 * As the scope of this project does not include either of those data stores the repository
 * itself is where requested data will be generated as if it had come from a database or server
 * and when new data is submitted it will handle it accordingly.
 */
class SnackRepository {

    // List of available snack items, pre-populated with the default list.
    // Ordinarily, this data would be retrieved from the database or remote server.
    private val snackList = mutableListOf<SnackItem>()

    /**
     * Retrieves the current list of snacks.
     *
     * This would be where we would make a call to the API and/or
     * the database, instead we simply return the list hardcoded here.
     */
    fun getSnackList(): List<SnackItem> {
        if(snackList.isEmpty()) {
            generateSnackList()
        }
        return snackList
    }

    /**
     * Retrieves a list of all currently checked snacks.
     */
    fun getCheckedSnackList(): List<SnackItem> {
        val result = mutableListOf<SnackItem>()
        for(snack in snackList) {
            if(snack.isChecked)
                result.add(snack)
        }
        return result
    }


    fun addNewSnack(snack: SnackItem) {
        snackList.add(snack)
    }

    /**
     * Stub for API call to submit list of snacks.
     *
     * Instead we just reset the checked status.
     */
    fun submitOrder(order: List<SnackItem>) {
        // Here we would make an API call to submit the order.
        // Instead we just reset the checkmarks
        for(snack in snackList)
            snack.isChecked = false
    }

    /**
     * Updates the checked status of a snack.
     */
    fun updateCheckStatus(snackItem: SnackItem, newCheck: Boolean) {
        for(snack in snackList) {
            if(snackItem == snack) {
                snack.isChecked = newCheck
            }
        }
    }

    /**
     * Generates a sample list of snacks.
     */
    private fun generateSnackList() {
        val sampleSnacks = listOf(SnackItem("French Fries", isVeg = true),
            SnackItem("Veggieburger", isVeg = true),
            SnackItem("Carrots", isVeg = true),
            SnackItem("Apple", isVeg = true),
            SnackItem("Banana", isVeg = true),
            SnackItem("Milkshake", isVeg = true),
            SnackItem("Hamburger", isVeg  = false),
            SnackItem("Cheeseburger", isVeg = false),
            SnackItem("Hot Dog", isVeg = false))

        snackList.addAll(sampleSnacks)
    }
}