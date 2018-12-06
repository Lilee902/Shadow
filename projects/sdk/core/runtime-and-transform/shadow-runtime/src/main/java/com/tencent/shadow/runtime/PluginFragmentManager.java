package com.tencent.shadow.runtime;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;

public class PluginFragmentManager {
    FragmentManager mBase;

    PluginFragmentManager(FragmentManager mBase) {
        this.mBase = mBase;
    }

    @SuppressLint("CommitTransaction")
    public PluginFragmentTransaction beginTransaction() {
        return new PluginFragmentTransaction(this, mBase.beginTransaction());
    }

    public ShadowFragment findFragmentByTag(String tag) {
        Fragment fragmentByTag = mBase.findFragmentByTag(tag);
        if (fragmentByTag instanceof IContainerFragment) {
            return ((IContainerFragment) fragmentByTag).getPluginFragment();
        } else {
            return null;
        }
    }

    public boolean executePendingTransactions() {
        return mBase.executePendingTransactions();
    }
}