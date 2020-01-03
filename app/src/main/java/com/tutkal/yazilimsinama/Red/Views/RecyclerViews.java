package com.tutkal.yazilimsinama.Red.Views;

import android.content.Context;


import com.tutkal.yazilimsinama.Red.Adapter.RecyclerViewAdapter;
import com.tutkal.yazilimsinama.Red.Adapter.RecyclerViewOnClick;

import java.util.ArrayList;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViews {
    private Context context;
    public RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerViewOnClick recyclerViewOnClick;
    private RecyclerView recyclerView;
    private ArrayList arrayList;
    private int recyclerViewLayoutID;
    private RecyclerViewOnClick clickListener;
    private int itemCount;
    int []itemID;
    public RecyclerViews(Context context) {
        this.context = context;
    }

    public RecyclerViews RecyclerView(RecyclerView recyclerVieww, ArrayList arrayList, int RecyclerViewLayoutID, RecyclerViewOnClick clickListener){
        this.recyclerView=recyclerVieww;
        this.arrayList=arrayList;
        this.recyclerViewLayoutID=RecyclerViewLayoutID;
        this.clickListener=clickListener;



        return this;
    }
    public RecyclerViews addItem(int itemCount, int... ItemID){
        this.itemCount=itemCount;
        this.itemID=ItemID;


        return this;
    }



    public void SetClickListener(RecyclerViewOnClick recyclerViewOnClick) {
            this.recyclerViewOnClick = recyclerViewOnClick;
    }
    public void Creat(){
        recyclerViewAdapter=new RecyclerViewAdapter(context,arrayList,recyclerViewLayoutID,clickListener,itemCount,itemID);

       // recyclerViewAdapter.notifyDataSetChanged();
        LinearLayoutManager mLayoutmanager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
