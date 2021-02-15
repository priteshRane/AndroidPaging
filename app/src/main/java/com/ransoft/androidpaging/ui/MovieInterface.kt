package com.ransoft.androidpaging.ui

import android.view.View
import com.ransoft.androidpaging.data.db.entities.Movie

interface MovieInterface {
    fun onDelete(view: View, movie: Movie)
}