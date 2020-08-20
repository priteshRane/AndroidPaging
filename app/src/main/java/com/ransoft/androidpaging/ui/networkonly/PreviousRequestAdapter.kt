package com.ransoft.androidpaging.ui.networkonly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ransoft.androidpaging.R
import com.ransoft.androidpaging.data.model.PreviousRequest
import kotlinx.android.synthetic.main.previous_request_item.view.*
import java.lang.reflect.Array.get


class PreviousRequestAdapter :
    PagedListAdapter<PreviousRequest, PreviousRequestAdapter.PreviousRequestViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreviousRequestAdapter.PreviousRequestViewHolder {
        return PreviousRequestViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.previous_request_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: PreviousRequestAdapter.PreviousRequestViewHolder,
        position: Int
    ) {
        val previousRequest: PreviousRequest = getItem(position)!!
        holder.bind(previousRequest, position)
    }

    class PreviousRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ticketNo = itemView.tv_ticket_no
        val raisedOn = itemView.tv_raised_on
        val status = itemView.tv_status

        fun bind(
            previousRequest: PreviousRequest,
            position: Int
        ) {
            ticketNo.text = previousRequest.ticketNo
            raisedOn.text = previousRequest.raisedOn
            status.text = previousRequest.status
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<PreviousRequest>() {
            override fun areItemsTheSame(oldPreviousRequest: PreviousRequest,
                                         newPreviousRequest: PreviousRequest) = oldPreviousRequest._id == newPreviousRequest._id

            override fun areContentsTheSame(oldPreviousRequest: PreviousRequest,
                                            newPreviousRequest: PreviousRequest) = oldPreviousRequest == newPreviousRequest
        }
    }
}