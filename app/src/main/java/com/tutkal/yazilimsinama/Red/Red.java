package com.tutkal.yazilimsinama.Red;

import android.content.Context;

import com.tutkal.yazilimsinama.Red.Views.RecyclerViews;
import com.tutkal.yazilimsinama.Red.Views.SharedPreferences;
import com.tutkal.yazilimsinama.Red.Views.Views;

public class Red {

    private Context mContext;
    private static Red Instance;
    private Views views;
    private RecyclerViews recyclerViews;
    private SharedPreferences sharedPreferences;

    public Red(Context context) {
        this.mContext=context;
        Instance=this;
    }

    public static Red getInstance() {
        return Instance;
    }

    public Views getViews() {
        return new Views();
    }

    public RecyclerViews getRecyclerViews() {
        return new RecyclerViews(mContext);
    }

    public SharedPreferences getSharedPreferences() {
        return new SharedPreferences(mContext);
    }
}
