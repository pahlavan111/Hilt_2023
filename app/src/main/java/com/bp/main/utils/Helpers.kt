package com.bp.main.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bp.hilt.R


fun Context.toast(message: String) {
    val mToast = Toast(applicationContext)
    val tView = LayoutInflater.from(applicationContext).inflate(R.layout.custom_toast, null)
    val txt = tView.findViewById<TextView>(R.id.toast_textView)
    txt.text = message
    mToast.view = tView
    mToast.duration = Toast.LENGTH_SHORT
    mToast.show()

}

fun AppCompatActivity.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

fun Context.getColumnNumber(): Int {
    val pref = applicationContext.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
    return pref.getInt("col_num", 2)
}

fun Context.setColumnNumber() {
    val pref = applicationContext.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
    val colNum = pref.getInt("col_num", 2)
    val editor = pref.edit()

    if (colNum == 1) {
        editor.putInt("col_num", 2)

    } else {
        editor.putInt("col_num", 1)
    }
    editor.apply()
}
