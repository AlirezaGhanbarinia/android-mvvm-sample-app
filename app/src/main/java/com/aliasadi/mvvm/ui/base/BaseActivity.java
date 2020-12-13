package com.aliasadi.mvvm.ui.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Ali Asadi on 07/01/2019.
 */
public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

    protected VM viewModel;

    @NonNull
    protected abstract VM createViewModel();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
    }

}
