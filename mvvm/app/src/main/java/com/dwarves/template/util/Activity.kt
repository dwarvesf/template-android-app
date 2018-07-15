package com.dwarves.template.util

import android.app.Activity
import android.widget.Toast

fun Activity.showError(message: String, length: Int) {
    Toast.makeText(this, message, length).show()
}
