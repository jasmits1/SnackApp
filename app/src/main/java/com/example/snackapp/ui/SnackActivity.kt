package com.example.snackapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.snackapp.R
import com.example.snackapp.model.SnackItem
import com.example.snackapp.model.SnackRepository
import com.example.snackapp.presenter.SnackPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SnackActivity : AppCompatActivity(), SnackActivityView {
    private final val STATE_LIST = "State Adapter Data"
    private lateinit var snackPresenter: SnackPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var submitButton: Button
    private lateinit var addButton: Button
    private lateinit var vegCheckBox: CheckBox
    private lateinit var notVegCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_snack)
        snackPresenter = SnackPresenter(this, SnackRepository())
        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.new_item_button)
        submitButton = findViewById(R.id.submit_button)
        vegCheckBox = findViewById(R.id.veggie_filter)
        notVegCheckBox = findViewById(R.id.non_veggie_filter)

        addButton.setOnClickListener(this::addNewItemClickListener)
        submitButton.setOnClickListener(this::submitClickListener)

        vegCheckBox.setOnClickListener(this::filterCheckListener)
        notVegCheckBox.setOnClickListener(this::filterCheckListener)

        if (savedInstanceState != null) {
            val list = savedInstanceState.getParcelableArrayList<SnackItem>(STATE_LIST) as List<SnackItem>
            snackPresenter.updateSnackList(list)
        }
    }

    override fun onResume() {
        super.onResume()
        snackPresenter.getSnackList()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(STATE_LIST, (recyclerView.adapter as SnackListAdapter).getSnackList())
    }

    override fun setSnackList(snackList: List<SnackItem>) {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = SnackListAdapter(snackList) { i: SnackItem, b: Boolean ->
                snackPresenter.onCheckStatusChanged(i, b)
            }
        } else {
            (recyclerView.adapter as SnackListAdapter).notifyDataSetChanged()
        }
    }

    private fun filterCheckListener(view: View) {
        val adapter = recyclerView.adapter as SnackListAdapter
        adapter.filterIsVeg(vegCheckBox.isChecked, notVegCheckBox.isChecked)
        snackPresenter.getSnackList()
    }

    private fun addNewItemClickListener(view: View) {
        val customLayout: View = layoutInflater.inflate(R.layout.add_item_dialog, null)
        MaterialAlertDialogBuilder(this)
                .setTitle("New Snack")
                .setView(customLayout)
                .setPositiveButton("Save") { dialog, which ->
                    val newSnack = SnackItem(
                            customLayout.findViewById<EditText>(R.id.new_item_text).text.toString(),
                            customLayout.findViewById<ToggleButton>(R.id.veggie_toggle).isChecked)
                    snackPresenter.addNewSnack(newSnack)
                }
                .setNegativeButton("Cancel") {dialog, which -> }
                .show()
    }

    private fun submitClickListener(view: View) {
        val order = snackPresenter.getCheckedSnacks()
        val orderSummary = StringBuilder()

        for (item in order) {
            orderSummary.append(item.name + "\n")
        }


        MaterialAlertDialogBuilder(this)
                .setTitle("Order Summary")
                .setMessage(orderSummary)
                .setNeutralButton("Dismiss") { dialog, which ->
                    snackPresenter.submitOrder(order)
                }
                .show()
    }

}