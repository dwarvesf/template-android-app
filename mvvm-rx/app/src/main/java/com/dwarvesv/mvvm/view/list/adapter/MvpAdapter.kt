package com.dwarvesv.mvvm.view.list.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dwarvesv.mvvm.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MvpAdapter :
        ListAdapter<com.dwarvesv.mvvm.data.model.User, MvpViewHolder>(DataDiffCallback()) {

    private val clickSubject = PublishSubject.create<com.dwarvesv.mvvm.data.model.User>()
    val clickEvent: Observable<com.dwarvesv.mvvm.data.model.User> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvpViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MvpViewHolder(inflater.inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: MvpViewHolder, position: Int) {
        holder.bind(getItem(position), clickSubject)
    }

}