package com.unite.push;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pushapplication.R;
import com.example.pushapplication.UnitePushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnitePushManager.readPkgMeta(this);
    }
}