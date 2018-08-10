package {{packageName}}.view.list.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import {{packageName}}.R
import {{packageName}}.data.model.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MvpAdapter :
        ListAdapter<User, MvpViewHolder>(DataDiffCallback()) {

    private val clickSubject = PublishSubject.create<User>()
    val clickEvent: Observable<User> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvpViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MvpViewHolder(inflater.inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: MvpViewHolder, position: Int) {
        holder.bind(getItem(position), clickSubject)
    }

}