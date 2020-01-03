package com.tutkal.yazilimsinama.model.Alagan.Class;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class AlaganJsonDatabase {
    public JSONObject jsonBlock = new JSONObject();
    public Context main;

    public static AlaganJsonDatabase Instance;
    String command;
    public AlaganJsonDatabase(Context main)
    {
        this.main = main;
        Instance = this;
    }
    public AlaganJsonDatabase put(String key, String value)
    {
        try
        {
            jsonBlock.put(key, value);
        }
        catch (JSONException e)
        {

        }
        return this;
    }
    public AlaganJsonDatabase setCommand(String command)
    {
        this.command = command;
        return this;
    }
    public void read(final AlaganJsonListener listener)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(main);
        JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST, "http://192.168.1.37/yazilim_sinama_proje/api.php"+command, jsonBlock, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("asfasgasg", "onErrorResponse: asfasfasfas"+error.getMessage());
            }
        });
        requestQueue.add(json);
        jsonBlock = new JSONObject();
    }
    public interface AlaganJsonListener {
        public void onResponse(JSONObject response);
    }
}
