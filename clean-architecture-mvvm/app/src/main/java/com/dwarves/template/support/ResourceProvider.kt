package {{packageName}}.support

import android.content.Context
import android.content.res.Resources
import android.support.annotation.ColorRes
import android.support.annotation.PluralsRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat

interface ResourceProvider {
    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int, vararg args: Any): String
    fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg args: Any): String
    fun getStringArray(id: Int): Array<String>
    fun getColor(@ColorRes id: Int): Int
    fun getDimension(id: Int): Float
    fun getDimensionPixelOffset(id: Int): Int
}

/**
 * When using this consider the context gonna be Service, Activity or Application
 * Different context may provider different result
 */
class ResourceProviderImpl(private val context: Context): ResourceProvider {
    private val resources: Resources = context.resources

    override fun getString(@StringRes id: Int): String {
        return resources.getString(id)
    }

    override fun getString(@StringRes id: Int, vararg args: Any): String {
        return resources.getString(id, *args)
    }

    override fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg args: Any): String {
        return resources.getQuantityString(id, quantity, *args)
    }

    override fun getStringArray(id: Int): Array<String> {
        return resources.getStringArray(id)
    }

    override fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    override fun getDimension(id: Int): Float {
        return resources.getDimension(id)
    }

    override fun getDimensionPixelOffset(id: Int): Int {
        return resources.getDimensionPixelOffset(id)
    }
}
