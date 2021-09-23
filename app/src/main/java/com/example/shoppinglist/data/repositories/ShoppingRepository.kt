package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {

    suspend fun upsert(item: ShoppingItem) = db.getShopppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShopppingDao().delete(item)

    fun getAllShoppingItems() = db.getShopppingDao().getAllShoppingItems()

}