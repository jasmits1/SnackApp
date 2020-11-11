package com.example.snackapp.ui

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.snackapp.R
import com.example.snackapp.inflate
import com.example.snackapp.model.SnackItem


class SnackListAdapter(private val snackList: List<SnackItem>, private val listener: (Int, Boolean) -> Unit)
    : RecyclerView.Adapter<SnackListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(parent.context, R.layout.item_snack_recycler_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(snackList[position], listener)
    }

    override fun getItemCount(): Int {
        return snackList.size
    }

    class ViewHolder(snackView: View) : RecyclerView.ViewHolder(snackView) {
        fun bind(snackItem: SnackItem, listener: (Int, Boolean) -> Unit) = with(itemView) {
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
                listener(adapterPosition, b)
            }

        }
    }
}