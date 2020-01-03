package com.tutkal.yazilimsinama.model.database;

import android.content.Context;

import com.tutkal.yazilimsinama.model.Alagan.Alagan;
import com.tutkal.yazilimsinama.model.Alagan.Class.AlaganStringDatabase;
import com.tutkal.yazilimsinama.model.IResponse;

import org.json.JSONObject;

public class Login {

    private String username,password;

    public Login() {
    }

    public void LoginController(final Context context, final IResponse Iresponse){
        Alagan.Instance.dbString.put("command","userLogin")
                .put("username",getUsername())
                .put("password",getPassword())
                .read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            // Red.getInstance().getSharedPreferences().SharedSet(context,"ID", object.getInt("tk_id"));
                            if (object.getString("response").equals("-1")){
                                Iresponse.callBack("-1");
                            }else {
                                Iresponse.callBack(object.getString("status"));
                            }
                        }catch (Exception e){
                        }
                    }
                });
    }


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
