package com.example.espresso

import android.content.Context

// Declared this class as open to test with mockito
open class ItemRepository(context: Context) {

    private val itemModel: ItemModel = ItemModel(context)

    fun getFavoriteItem() = itemModel.getFavoriteItems()

    fun saveFavoriteItem(favItem: ItemDataClass) = itemModel.saveFavoriteItems(favItem)

    fun removeFavoriteItem() = itemModel.removeFavoriteItems()
}