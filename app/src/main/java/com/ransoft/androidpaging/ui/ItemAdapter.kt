package com.ransoft.androidpaging.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ransoft.androidpaging.R
import com.ransoft.androidpaging.data.db.entities.Item
import kotlinx.android.synthetic.main.item_item.view.*


class ItemAdapter :
    PagedListAdapter<Item, ItemAdapter.ItemViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val itemEntitiy: Item = getItem(position)!!
        holder.bind(itemEntitiy, position)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ticketNo = itemView.tv_ticket_no
        val raisedOn = itemView.tv_raised_on
        val status = itemView.tv_status

        fun bind(
            itemEntitiy: Item,
            position: Int
        ) {
            ticketNo.text = position.toString() + ") " + itemEntitiy.owner!!.user_id.toString()
            raisedOn.text = itemEntitiy.owner!!.display_name
            status.text = itemEntitiy.owner!!.accept_rate.toString()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item,
                                         newItem: Item) = oldItem.answer_id == newItem.answer_id

            override fun areContentsTheSame(oldItem: Item,
                                            newItem: Item) = oldItem == newItem
        }
    }
}