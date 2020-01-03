package com.tutkal.yazilimsinama.Red.Views;

import android.content.Context;
import android.preference.PreferenceManager;

public class SharedPreferences {

    private Context mContext;

    public SharedPreferences(Context context) {
        this.mContext = context;
    }

    public  void SharedSet(Context context, String SharedID, int value){
        android.content.SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(SharedID, value);
        editor.commit();
    }

    public  void SharedGet(Context context, String SharedID , RedSharedListener listener){
        int value;
        android.content.SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        value = preferences.getInt(SharedID, -1);
        listener.onResponse(value);
    }

    public  void SharedRemove(Context context, String SharedID){
        android.content.SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SharedID);
        editor.commit();
    }

    public interface RedSharedListener{
        void onResponse(int id);
    }
}
