package com.tutkal.yazilimsinama.Screen.Activity.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tutkal.yazilimsinama.R;
import com.tutkal.yazilimsinama.Red.Red;
import com.tutkal.yazilimsinama.Screen.Fragment.Student.ExamFragment;
import com.tutkal.yazilimsinama.Screen.Fragment.Student.ProfilFragment;
import com.tutkal.yazilimsinama.Screen.Fragment.Student.StatisticsFragment;
import com.tutkal.yazilimsinama.model.Alagan.Alagan;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        init();

    }

    private void init() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        new Alagan(getApplicationContext());
        new Red(getApplicationContext());
        BottomNavigationViewEx bottomNavigationView=findViewById(R.id.ActivityStudentBottomNavigationView);
        FrameLayout content=findViewById(R.id.ActivityStudentContent);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new ExamFragment());
    }
    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_statistics:
                    loadFragment(new StatisticsFragment());
                    return true;

                case R.id.navigation_exam:
                    loadFragment(new ExamFragment());
                    return true;

                case R.id.navigation_profil:
                    loadFragment(new ProfilFragment());
                    return true;

            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ActivityStudentContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
