package {{packageName}}.ui.products.list

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import {{packageName}}.R
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

class ProductListAdapter : ListAdapter<ProductItemViewModel, ProductItemViewHolder>(ProductDiffCallback()) {
    private val events = PublishRelay.create<Pair<Long, EventType>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_product, parent, false)

        return ProductItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.loading.visibility = if (item.loading) View.VISIBLE else View.GONE
        holder.itemView.setOnClickListener {
            events.accept(Pair(item.id, EventType.CLICK))
        }
        holder.ivRemove.setOnClickListener {
            events.accept(Pair(item.id, EventType.REMOVE))
        }
    }

    fun getEvents(): Observable<Pair<Long, EventType>> = events.hide()

    enum class EventType {
        CLICK, REMOVE
    }
}
