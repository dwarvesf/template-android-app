package com.dwarves.template.support

import android.content.Context
import android.support.annotation.MainThread
import android.widget.Toast
import io.reactivex.Completable

interface Navigator {
    fun toProductDetail(id: Long): Completable
}

// Use to navigate between activities
// Careful about context, different context will provide different result
class NavigatorImpl(private val context: Context): Navigator {

    @MainThread
    override fun toProductDetail(id: Long): Completable {
        return Completable.fromAction {
            // val intent = Intent(context, ProductDetail::class.java)
            Toast.makeText(context, "Open $id", Toast.LENGTH_SHORT).show()
        }
    }
}
