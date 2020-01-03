package com.tutkal.yazilimsinama.Screen.Fragment.Student;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.tutkal.yazilimsinama.R;
import com.tutkal.yazilimsinama.Red.Adapter.RecyclerClass;
import com.tutkal.yazilimsinama.Red.Adapter.RecyclerViewOnClick;
import com.tutkal.yazilimsinama.Red.Red;
import com.tutkal.yazilimsinama.Red.Views.RecyclerViews;
import com.tutkal.yazilimsinama.Screen.Activity.LoginActivity;
import com.tutkal.yazilimsinama.model.Alagan.Alagan;
import com.tutkal.yazilimsinama.model.Alagan.Class.AlaganStringDatabase;
import com.tutkal.yazilimsinama.model.database.Login;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProfilFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<RecyclerClass> arrayList=new ArrayList<>();
    private RecyclerViews k = Red.getInstance().getRecyclerViews();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student_profil, container, false);
        init(view);
        new Red(getContext());

        arrayList.clear();
        Alagan.Instance.dbString
                .put("command","listExam")
                .put("userID","2")
                .read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText(getApplicationContext(), ""+(response), Toast.LENGTH_SHORT).show();
                            JSONArray jsonArray = new JSONArray(response);
                            Log.e("asdad",response);
                            for (int i = 0; i<jsonArray.length();i++)
                            {
                                JSONObject objs = jsonArray.getJSONObject(i);
                                arrayList.add(new RecyclerClass(objs.getInt("tk_id"),(i+1)+" Sınav",objs.getString("tk_date")));

                            }
                            k.recyclerViewAdapter.notifyDataSetChanged();

                        }catch (Exception e){
                        }

                    }
                });

        k.addItem(3,R.id.Item_ToDoList_Name,R.id.Item_ToDoList_Date,
                R.id.Item_ToDoList_Counter)
                .RecyclerView(recyclerView, arrayList, R.layout.item_recyclerviews, new RecyclerViewOnClick() {
                    @Override
                    public void OnClick(int ID) {

                    }
                }).Creat();



        return view;
    }

    private void init(View view) {
        recyclerView=view.findViewById(R.id.recyclerview);
        Button btnExit=view.findViewById(R.id.FragmentbuttonExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
    }


}
