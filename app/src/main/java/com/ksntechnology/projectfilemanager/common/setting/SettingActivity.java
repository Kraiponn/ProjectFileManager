package com.ksntechnology.projectfilemanager.common.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ksntechnology.projectfilemanager.R;

public class SettingActivity extends AppCompatActivity {
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolBar = findViewById(R.id.toolBar2);
        setSupportActionBar(toolBar);

        if (findViewById(R.id.ContainerPref) != null) {
            if (savedInstanceState != null) {
                return;
            }

            /*getSupportFragmentManager()
                    .beginTransaction()
                    .add(
                            R.id.ContentContainer,
                            new SettingFragment()
                    ).commit();*/
        }
    }

}
