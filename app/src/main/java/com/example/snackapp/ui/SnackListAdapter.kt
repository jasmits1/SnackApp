package com.example.snackapp.ui

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.snackapp.R
import com.example.snackapp.inflate
import com.example.snackapp.model.SnackItem


class SnackListAdapter(private val snackList: List<SnackItem>, private val listener: (SnackItem, Boolean) -> Unit)
    : RecyclerView.Adapter<SnackListAdapter.ViewHolder>() {

    private var filteredSnackList = mutableListOf<SnackItem>()
    private var filtering = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(parent.context, R.layout.item_snack_recycler_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(filtering) {
            holder.bind(filteredSnackList[position], listener)
        } else if(!filtering) {
            holder.bind(snackList[position], listener)
        }
    }

    override fun getItemCount(): Int {
        if(filtering) {
            return filteredSnackList.size
        } else {
            return snackList.size
        }
    }

     fun getSnackList(): ArrayList<SnackItem> {
        return snackList.toMutableList() as ArrayList<SnackItem>
    }

    fun filterIsVeg(isVeg: Boolean, notVeg: Boolean) {
        filteredSnackList.clear()
        if(isVeg && notVeg) {
            filtering = false
        }
        else {
            filtering = true
            if (isVeg) {
                for (snack in snackList) {
                    if (snack.isVeg)
                        filteredSnackList.add(snack)
                    else
                        snack.isChecked = false
                }
            }
            if (notVeg) {
                for (snack in snackList) {
                    if (!snack.isVeg)
                        filteredSnackList.add(snack)
                    else
                        snack.isChecked = false
                }
            }
        }
    }


    class ViewHolder(snackView: View) : RecyclerView.ViewHolder(snackView) {
        fun bind(snackItem: SnackItem, listener: (SnackItem, Boolean) -> Unit) = with(itemView) {
            val checkBox = itemView.findViewById(R.id.item_CheckBox) as CheckBox
            checkBox.text = snackItem.name
            if(snackItem.isVeg) {
                checkBox.setTextColor(Color.GREEN)
            }
            else {
                checkBox.setTextColor(Color.RED)
            }
            checkBox.isChecked = snackItem.isChecked
            checkBox.setOnCheckedChangeListener { compoundButton, b ->
                listener(snackItem, b)
            }

        }
    }
}