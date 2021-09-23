package com.example.shoppinglist.ui.shoppingList

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var addDialogListner: AddDialogListner): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)
        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all information!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = ShoppingItem(name,amount.toInt(),false)
            addDialogListner.onAddButtonClick(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            dismiss()
        }
    }

}