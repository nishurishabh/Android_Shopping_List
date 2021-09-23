package com.example.shoppinglist.ui.shoppingList

import com.example.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListner {

    fun onAddButtonClick(item: ShoppingItem)

}