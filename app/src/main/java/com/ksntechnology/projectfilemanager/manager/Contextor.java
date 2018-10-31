package com.ksntechnology.projectfilemanager.manager;

import android.content.Context;

public class Contextor {
    private static Contextor INSTANCE;
    private Context mContext;

    public static Contextor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Contextor();
        }

        return INSTANCE;
    }

    public void init(Context context) {
        mContext =  context;
    }

    public Context getContext() {
        return mContext;
    }


}
