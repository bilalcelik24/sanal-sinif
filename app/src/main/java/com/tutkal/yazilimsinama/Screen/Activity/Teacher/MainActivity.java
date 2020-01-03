package com.tutkal.yazilimsinama.Screen.Activity.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tutkal.yazilimsinama.R;
import com.tutkal.yazilimsinama.Red.Red;
import com.tutkal.yazilimsinama.Screen.Activity.LoginActivity;
import com.tutkal.yazilimsinama.model.Alagan.Alagan;
import com.tutkal.yazilimsinama.model.Alagan.Class.AlaganStringDatabase;
import com.tutkal.yazilimsinama.model.database.Exam;

public class MainActivity extends AppCompatActivity {
    private Spinner spnCorrectAnswer,spnCategory;
    private EditText edtQuestion,edtAnswerA,edtAnswerB,edtAnswerC,edtAnswerD;
    private Button btnAddQuestions,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        init();
        final Exam addQuestions=new Exam();
        btnAddQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestions.setQuestions(edtQuestion.getText().toString());
                addQuestions.setAnswerA(edtAnswerA.getText().toString());
                addQuestions.setAnswerB(edtAnswerB.getText().toString());
                addQuestions.setAnswerC(edtAnswerC.getText().toString());
                addQuestions.setAnswerD(edtAnswerD.getText().toString());
                addQuestions.setCategory(String.valueOf(spnCategory.getSelectedItemPosition()+1));
                addQuestions.setCorrectAnswer(String.valueOf(spnCorrectAnswer.getSelectedItemPosition()+1));
                Log.e("=",addQuestions.getAnswerA()+addQuestions.getAnswerB()+addQuestions.getAnswerC()
                +addQuestions.getAnswerD()+addQuestions.getQuestions()+addQuestions.getCorrectAnswer()+addQuestions.getCategory());

                Alagan.Instance.dbString
                        .put("command","questionInsert")
                        .put("question",addQuestions.getQuestions())
                        .put("answerA",addQuestions.getAnswerA())
                        .put("answerB",addQuestions.getAnswerB())
                        .put("answerC",addQuestions.getAnswerC())
                        .put("answerD",addQuestions.getAnswerD())
                        .put("correct",addQuestions.getCorrectAnswer())
                        .put("category",addQuestions.getCategory())
                        .read(new AlaganStringDatabase.AlaganListener() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("1")){
                                    Toast.makeText(MainActivity.this, "Soru Eklendi", Toast.LENGTH_SHORT).show();
                                    edtQuestion.setText("");
                                    edtAnswerA.setText("");
                                    edtAnswerB.setText("");
                                    edtAnswerC.setText("");
                                    edtAnswerD.setText("");
                                }
                            }
                        });
            }
        });
    }

    private void init() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        spnCategory=findViewById(R.id.spinnerCategory);
        spnCorrectAnswer=findViewById(R.id.spinnerCorrectAnswer);
        edtQuestion=findViewById(R.id.editTextQuestions);
        edtAnswerA=findViewById(R.id.editTextAnswerA);
        edtAnswerB=findViewById(R.id.editTextAnswerB);
        edtAnswerC=findViewById(R.id.editTextAnswerc);
        edtAnswerD=findViewById(R.id.editTextAnswerD);
        btnAddQuestions=findViewById(R.id.buttonAddQuestions);
        btnExit=findViewById(R.id.buttonExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        new Red(getApplicationContext());
        new Alagan(getApplicationContext());
        Red.getInstance().getViews().Spinner(getApplicationContext(),spnCategory,
               "Tarih","Cografya","Genel Kültür","Spor","Matematik");
        Red.getInstance().getViews().Spinner(getApplicationContext(),spnCorrectAnswer,
                "Doğru Cevap A","Doğru Cevap B","Doğru Cevap C","Doğru Cevap D");
    }
}
