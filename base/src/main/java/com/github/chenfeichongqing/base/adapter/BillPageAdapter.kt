package com.github.chenfeichongqing.base.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eyepetizer.android.extension.inflate
import com.github.chenfeichongqing.base.R
import com.github.chenfeichongqing.base.data.model.bean.BillInfo

class BillPageAdapter : PagingDataAdapter<BillInfo, BillPageAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(R.layout.item_main_bill.inflate(parent))
        holder.itemView.setOnClickListener {
            getItem(holder.bindingAdapterPosition)?.let {
              //  ActionUrlUtil.process(fragment, it.actionUrl, it.title ?: "")
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.run {
            holder.tvTitle.text = title
            holder.tvTime.text = time
            holder.tvContent.text = remark
            holder.tvMoney.text = money.toString()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.name_text)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        val tvContent: TextView = view.findViewById(R.id.tv_decribe)
        val tvType: TextView = view.findViewById(R.id.star_type_text)
        val tvMoney: TextView = view.findViewById(R.id.star_count_text)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BillInfo>() {
            override fun areItemsTheSame(oldItem: BillInfo, newItem: BillInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BillInfo, newItem: BillInfo): Boolean {
                return oldItem == newItem
            }
        }

    }

}