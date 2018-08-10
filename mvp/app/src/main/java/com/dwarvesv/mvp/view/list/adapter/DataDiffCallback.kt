package {{packageName}}.view.list.adapter

import android.support.v7.util.DiffUtil
import {{packageName}}.data.model.User

class DataDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}