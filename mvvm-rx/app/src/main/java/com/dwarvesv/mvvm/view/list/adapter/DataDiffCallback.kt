package {{packageName}}.view.list.adapter

import android.support.v7.util.DiffUtil

class DataDiffCallback : DiffUtil.ItemCallback<{{packageName}}.data.model.User>() {
    override fun areItemsTheSame(oldItem: {{packageName}}.data.model.User, newItem: {{packageName}}.data.model.User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: {{packageName}}.data.model.User, newItem: {{packageName}}.data.model.User): Boolean {
        return oldItem == newItem
    }

}