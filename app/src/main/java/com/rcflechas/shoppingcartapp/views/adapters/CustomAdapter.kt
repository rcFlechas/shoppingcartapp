package com.rcflechas.shoppingcartapp.views.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class CustomAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<BaseViewHolder<*>>() {
    var elements: MutableList<T> = mutableListOf()

    fun animateTo(models: MutableList<T>) {
        applyAndAnimateRemovals(models)
        applyAndAnimateAdditions(models)
        applyAndAnimateMovedItems(models)
    }

    private fun applyAndAnimateRemovals(newModels: MutableList<T>) {
        for (i in elements.indices.reversed()) {
            val model = elements[i]
            if (!newModels.contains(model)) {
                removeItem(i)
            }
        }
    }

    private fun applyAndAnimateAdditions(newModels: MutableList<T>) {
        var i = 0
        val count = newModels.size
        while (i < count) {
            val model = newModels[i]
            if (!elements.contains(model)) {
                addItem(i, model)
            }
            i++
        }
    }

    private fun applyAndAnimateMovedItems(newModels: MutableList<T>) {
        for (toPosition in newModels.indices.reversed()) {
            val model = newModels[toPosition]
            val fromPosition = elements.indexOf(model)
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition)
            }
        }
    }

    private fun removeItem(position: Int): T {
        val model = elements.removeAt(position)
        notifyItemRemoved(position)
        return model
    }

    private fun addItem(position: Int, model: T) {
        elements.add(position, model)
        notifyItemInserted(position)
    }

    private fun moveItem(fromPosition: Int, toPosition: Int) {
        val model = elements.removeAt(fromPosition)
        elements.add(toPosition, model)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun getItemCount(): Int {
        return elements.size
    }
}