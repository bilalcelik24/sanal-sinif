package com.tutkal.yazilimsinama.model.Alagan;

import android.content.Context;

import com.tutkal.yazilimsinama.model.Alagan.Class.AlaganJsonDatabase;
import com.tutkal.yazilimsinama.model.Alagan.Class.AlaganStringDatabase;


public class Alagan {
    private Context main;

    public static Alagan Instance;

    public AlaganStringDatabase dbString;
    public AlaganJsonDatabase dbJson;


    public Alagan(Context main)
    {
        this.main = main;
        Instance = this;
        dbString = new AlaganStringDatabase(main);
        dbJson = new AlaganJsonDatabase(main);

    }



}
