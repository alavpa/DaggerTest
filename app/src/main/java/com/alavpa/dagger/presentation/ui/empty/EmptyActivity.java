package com.alavpa.dagger.presentation.ui.empty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alavpa.dagger.R;

/**
 * Created by alavpa on 8/01/17.
 */

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }
}
