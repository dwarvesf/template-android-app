package com.dwarvesv.mvvm.view.list.adapter

import android.support.v7.util.DiffUtil

class DataDiffCallback : DiffUtil.ItemCallback<com.dwarvesv.mvvm.data.model.User>() {
    override fun areItemsTheSame(oldItem: com.dwarvesv.mvvm.data.model.User, newItem: com.dwarvesv.mvvm.data.model.User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: com.dwarvesv.mvvm.data.model.User, newItem: com.dwarvesv.mvvm.data.model.User): Boolean {
        return oldItem == newItem
    }

}