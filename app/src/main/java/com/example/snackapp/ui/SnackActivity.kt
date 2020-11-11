package com.example.snackapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.snackapp.R
import com.example.snackapp.model.SnackItem
import com.example.snackapp.model.SnackRepository
import com.example.snackapp.presenter.SnackPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SnackActivity : AppCompatActivity(), SnackActivityView {
    private lateinit var snackPresenter: SnackPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)
        snackPresenter = SnackPresenter(this, SnackRepository())
        recyclerView = findViewById(R.id.recyclerView)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener(this::submitClickListener)
    }

    override fun onResume() {
        super.onResume()
        snackPresenter.getSnackList()
    }

    override fun setSnackList(snackList: List<SnackItem>) {
        recyclerView.adapter = SnackListAdapter(snackList) { i: Int, b: Boolean ->
            snackPresenter.onCheckStatusChanged(i, b)
        }
    }

    private fun submitClickListener(view: View) {
        val order = snackPresenter.getCheckedSnacks()
        val orderSummary = StringBuilder()

        for(item in order) {
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