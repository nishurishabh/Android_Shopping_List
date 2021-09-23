package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppingList.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.itemView.tvName.text = curShoppingItem.name
        holder.itemView.tvAmount.text = "${curShoppingItem.amount}"
        setVisibility(holder, curShoppingItem)

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.ivMinus.setOnClickListener{
            if(curShoppingItem.amount > 0)
            {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }

        holder.itemView.ivCart.setOnClickListener {
            val updatedItem = ShoppingItem(curShoppingItem.name, curShoppingItem.amount, true)
            viewModel.delete(curShoppingItem)
            viewModel.upsert(updatedItem)
        }
    }

    private fun setVisibility(holder: ShoppingViewHolder, curItem: ShoppingItem) {
        if(curItem.shopped) {
            holder.itemView.ivMinus.visibility = View.GONE
            holder.itemView.ivPlus.visibility = View.GONE
            holder.itemView.ivCart.visibility = View.GONE
            holder.itemView.ivDone.visibility = View.VISIBLE
        } else {
            holder.itemView.ivMinus.visibility = View.VISIBLE
            holder.itemView.ivPlus.visibility = View.VISIBLE
            holder.itemView.ivCart.visibility = View.VISIBLE
            holder.itemView.ivDone.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}

