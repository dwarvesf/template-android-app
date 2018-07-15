package com.dwarves.template.ui.products.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dwarves.template.R

class ProductItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.tvTitle)
    lateinit var tvTitle: TextView
    @BindView(R.id.tvDescription)
    lateinit var tvDescription: TextView
    @BindView(R.id.ivRemove)
    lateinit var ivRemove: ImageView
    @BindView(R.id.loading)
    lateinit var loading: View

    init {
        ButterKnife.bind(this, itemView)
    }
}
