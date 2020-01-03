package com.tutkal.yazilimsinama.Screen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.tutkal.yazilimsinama.R;
import com.tutkal.yazilimsinama.databinding.ActivityLoginBinding;
import com.tutkal.yazilimsinama.model.Alagan.Alagan;
import com.tutkal.yazilimsinama.model.IResponse;
import com.tutkal.yazilimsinama.model.database.Login;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        init();
        final Login Login=new Login();
        Login.setUsername(binding.Username.getText().toString());
        Login.setPassword(binding.Password.getText().toString());
        binding.ActivityLoginButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ActivityLoginProgressBar.setVisibility(View.VISIBLE);
                binding.ActivityLoginButtonLogin.setVisibility(View.GONE);
                Login.LoginController(getApplicationContext(),new IResponse() {
                    @Override
                    public void callBack(Object data) {
                        switch ((String)data){
                            case "-1":
                                Toast.makeText(LoginActivity.this, "Kullanıcı Adı veya Şifre Yalnış", Toast.LENGTH_SHORT).show();
                                break;
                            case "2":
                                finish();
                                startActivity(new Intent(LoginActivity.this, com.tutkal.yazilimsinama.Screen.Activity.Teacher.MainActivity.class));
                                break;
                            case "3":
                                finish();
                                startActivity(new Intent(LoginActivity.this, com.tutkal.yazilimsinama.Screen.Activity.Student.MainActivity.class));
                                break;
                                default:
                                    Toast.makeText(LoginActivity.this, "Bilinmeyen Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                                    break;
                        }
                        binding.ActivityLoginProgressBar.setVisibility(View.GONE);
                        binding.ActivityLoginButtonLogin.setVisibility(View.VISIBLE);
                       /* Red.getInstance().getSharedPreferences().SharedGet(getApplicationContext(), "ID", new SharedPreferences.RedSharedListener() {
                            @Override
                            public void onResponse(int id) {
                                Log.e("id",""+id);
                            }
                        });*/
                    }
                });
            }
        });
        binding.setLogin(Login);
    }

    private void init() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        new Alagan(getApplicationContext());
    }
}
