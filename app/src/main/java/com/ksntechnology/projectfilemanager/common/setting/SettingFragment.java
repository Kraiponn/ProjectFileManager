package com.ksntechnology.projectfilemanager.common.setting;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.ksntechnology.projectfilemanager.R;

public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference);
    }

    /*@Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.preference, s);
    }*/


}
