package com.example.projet_android.ui.alerts

import android.content.Context
import android.widget.Toast

fun showShortAlert(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}