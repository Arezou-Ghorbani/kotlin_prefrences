package arezou.ghorbani.s1_viewmodel

import arezou.ghorbani.s1_viewmodel.recycler.ItemModel

object Utils {

    fun getItems():MutableList<ItemModel>{
        val items : MutableList<ItemModel> = mutableListOf()

        for (i in 1..100){
            items.add(ItemModel(i,"Item $i"))
        }

        return items
    }

}