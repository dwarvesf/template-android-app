package {{packageName}}.ui.products.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductItemViewModel(
        val id: Long,
        val title: String,
        val description: String,
        val loading: Boolean = false
) : Parcelable
