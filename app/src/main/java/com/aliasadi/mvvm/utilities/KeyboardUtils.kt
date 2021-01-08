package com.aliasadi.mvvm.utilities

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object KeyboardUtils {
    fun hideSoftKeyboard(activity: Activity) {
        val view = activity.window.peekDecorView()
        if (view != null) {
            val inputmanger = activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputmanger.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideSoftKeyboard(context: Context, view: View) {
        view.clearFocus()
        val inputmanger = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputmanger.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showSoftKeyboard(activity: Activity) {
        showSoftKeyboard(activity, null)
    }

    fun showSoftKeyboard(context: Context, view: View?) {
        view!!.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
        val inputManager = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(view, 0)
    }

    fun toggleKeyboardState(context: Context, edit: EditText) {
        edit.isFocusable = true
        edit.isFocusableInTouchMode = true
        edit.requestFocus()
        val inputManager = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}