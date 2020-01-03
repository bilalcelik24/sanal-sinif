package com.tutkal.yazilimsinama.Red.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.WordsHolder> {

    private Context mContext;

    private LayoutInflater mInflater;
    private ArrayList<RecyclerClass> arrayList;
    private RecyclerViewOnClick clikk;
    private int inflaterLayout;
    private int itemsCount;


     List<Integer> listObjeID=new ArrayList<>();


    public RecyclerViewAdapter(Context context, ArrayList<RecyclerClass> words, int inflaterLayout,RecyclerViewOnClick clickListener,int itemsCount,int... items ){
        mInflater= LayoutInflater.from(context);
        arrayList =words;
        mContext=context;
        clikk = clickListener;
        this.inflaterLayout = inflaterLayout;
        this.itemsCount =itemsCount;
        for (int i = 0; i <itemsCount ; i++) {
            listObjeID.add(items[i]);
        }


    }



    @NonNull
    @Override
    public WordsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mInflater.inflate(inflaterLayout,parent,false);
        String name = mContext.getResources().getResourceEntryName(inflaterLayout);

        WordsHolder holder =new WordsHolder(view);

        return holder;

    }



    @Override
    public void onBindViewHolder(WordsHolder wordsHolder, final int position) {

        final RecyclerClass wordsClass= arrayList.get(position);
        for (int i = 0; i < itemsCount-1; i++) {
           wordsHolder.listObje.get(i).setText(wordsClass.getAs()[i]);
        }

        wordsHolder.listObje.get(itemsCount-1).setText(""+(position+1));
        wordsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clikk.OnClick(wordsClass.getID());
            }
        });








    }


    public void AddItem(int itemCount, int... items){


    }

    public void getID(){

    }
    public void filterList(ArrayList<RecyclerClass> filteredList ){
        arrayList=filteredList;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class WordsHolder extends RecyclerView.ViewHolder{

        List<TextView> listObje=new ArrayList<>();
        public WordsHolder(View itemView) {
            super(itemView);


            for (int i = 0; i < itemsCount; i++) {


                listObje.add((TextView) itemView.findViewById(listObjeID.get(i)));
            }



        }
    }

}
