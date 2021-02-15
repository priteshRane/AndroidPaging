package com.ransoft.androidpaging.util

import android.content.Context
import android.widget.Toast
import com.ransoft.androidpaging.ui.context

fun Context.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}