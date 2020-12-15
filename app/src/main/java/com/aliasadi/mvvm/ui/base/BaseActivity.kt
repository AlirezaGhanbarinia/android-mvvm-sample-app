package com.aliasadi.mvvm.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: VM

    protected abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }
}