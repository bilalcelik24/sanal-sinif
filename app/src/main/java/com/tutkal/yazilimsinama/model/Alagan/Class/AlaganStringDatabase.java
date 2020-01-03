package com.tutkal.yazilimsinama.model.Alagan.Class;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlaganStringDatabase
{
    Map<String, String> params = new HashMap<String, String>();

    private Context main;


    public AlaganStringDatabase(Context main)
    {
        this.main = main;
    }

    public AlaganStringDatabase put(String key, String value)
    {
        if (value == null)
            value = "";
        params.put(key,value);
        return this;
    }
    public AlaganStringDatabase put(String key, List<String> value)
    {
        String k = "";
        for (int i = 0 ; i<value.size();i++)
           k += value.get(i) + ">";
        k.substring(0,k.length()-1);
        params.put(key,k);
        return this;
    }

    public void read(final AlaganListener listener)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(main);


        StringRequest myReq = new StringRequest(Request.Method.POST, "http://192.168.1.37/yazilim_sinama_proje/api.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("asfasfasfasf",error.getMessage());
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {

                String strUTF8 = null;
                try {

                    params.clear();
                    strUTF8 = new String(response.data, "utf8");

                } catch (UnsupportedEncodingException e) {

                    e.printStackTrace();
                }
                return Response.success(strUTF8,
                        HttpHeaderParser.parseCacheHeaders(response));
            }
            protected Map<String, String> getParams()
                    throws com.android.volley.AuthFailureError {
                Map<String, String> K = new HashMap<String, String>();
                K = params;
                return K;
            }
        };
        myReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(myReq);
    }
    public interface AlaganListener {
        public void onResponse(String response);

    }
}
