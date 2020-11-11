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
    private val snackList = mutableListOf(
        SnackItem("French Fries", isVeg = true),
        SnackItem("Veggieburger", isVeg = true),
        SnackItem("Carrots", isVeg = true),
        SnackItem("Apple", isVeg = true),
        SnackItem("Banana", isVeg = true),
        SnackItem("Milkshake", isVeg = true),
        SnackItem("Hamburger", isVeg  = false),
        SnackItem("Cheeseburger", isVeg = false),
        SnackItem("Hot Dog", isVeg = false))

    fun getSnackList(): List<SnackItem> {
        return snackList
    }

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

    fun submitOrder(order: List<SnackItem>) {
        // Here we would make an API call to submit the order.
        // Instead we just reset the checkmarks
        for(snack in snackList)
            snack.isChecked = false
    }

    fun updateCheckStatus(position: Int, isChecked: Boolean) {
        //snackList[position].isChecked = !snackList[position].isChecked
        snackList[position].isChecked = isChecked
        println(snackList[position].isChecked)
    }
}