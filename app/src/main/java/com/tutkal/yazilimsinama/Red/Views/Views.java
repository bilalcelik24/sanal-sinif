package com.tutkal.yazilimsinama.Red.Views;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tutkal.yazilimsinama.R;

import java.util.ArrayList;

public class Views {



    public Views() {
    }

    public void Spinner(Context mContext, Spinner spinner, ArrayList<String> arrayList){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext, R.layout.support_simple_spinner_dropdown_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }
    public void Spinner(Context mContext, Spinner spinner, String... args){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext, R.layout.support_simple_spinner_dropdown_item,args);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }
}
