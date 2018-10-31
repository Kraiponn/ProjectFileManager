package com.ksntechnology.projectfilemanager.common.setting;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.ksntechnology.projectfilemanager.R;

public class PreffSettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(
                        R.id.ContainerPref,
                        new SettingFragment()
                ).commit();
    }



}
