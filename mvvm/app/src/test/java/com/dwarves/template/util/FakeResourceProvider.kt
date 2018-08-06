package com.dwarves.template.util

import com.dwarves.template.support.ResourceProvider
import com.nhaarman.mockito_kotlin.mock

class FakeResourceProvider : ResourceProvider by mock() {
    override fun getString(id: Int): String {
        return id.toString()
    }

    override fun getString(id: Int, vararg args: Any): String {
        var value = id.toString()
        args.forEach {
            value = "$value $it"
        }
        return value
    }
}
