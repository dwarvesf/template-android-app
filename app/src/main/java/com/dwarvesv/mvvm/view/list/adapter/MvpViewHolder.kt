package com.dwarvesv.mvvm.view.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class MvpViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!),
        LayoutContainer {

    fun bind(dataMvp: com.dwarvesv.mvvm.data.model.User, clickSubject: PublishSubject<com.dwarvesv.mvvm.data.model.User>) {

        itemView.setOnClickListener {
            clickSubject.onNext(dataMvp)
        }
        tvUserName.text = dataMvp.name
    }

}

