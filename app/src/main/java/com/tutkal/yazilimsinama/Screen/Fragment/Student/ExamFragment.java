package com.tutkal.yazilimsinama.Screen.Fragment.Student;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tutkal.yazilimsinama.R;
import com.tutkal.yazilimsinama.model.Alagan.Alagan;
import com.tutkal.yazilimsinama.model.Alagan.Class.AlaganStringDatabase;
import com.tutkal.yazilimsinama.model.Class.AddQuestions;
import com.tutkal.yazilimsinama.model.database.Exam;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ExamFragment extends Fragment {

    private  Thread t;
    private Button btnExamStart;
    private ConstraintLayout constExamScreen,constExamFinish;
    private ProgressBar progressBar;
    private Button btnAnswerA,btnAnswerB,btnAnswerC,btnAnswerD;
    private TextView txtQuestions,txtQuestionsCounter,txtCorrectAnswerCounter,txtWrongAnswerCounter;

    private ArrayList<AddQuestions> arrayListAnswer=new ArrayList<>();
    final int[] counter=new int[1];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student_exam, container, false);
        init(view);

        btnExamStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constExamScreen.setVisibility(View.VISIBLE);
                btnExamStart.setVisibility(View.GONE);
                Time();
            }
        });
        Alagan.Instance.dbString.put("command","questionGet")
                .read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("asdasd",response);
                        //Toast.makeText(getContext(), "dasd", Toast.LENGTH_SHORT).show();

                        final ArrayList<Exam> arrayList =new ArrayList<>();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject objs = jsonArray.getJSONObject(i);
                                Log.e(" asda",response);
                                //String questions, String answerA, String answerB, String answerC, String answerD, String correctAnswer, String category
                                arrayList.add(new Exam(objs.getString("tk_question"),objs.getString("tk_answerA"),objs.getString("tk_answerB")
                                        ,objs.getString("tk_answerC"),objs.getString("tk_answerD"),objs.getString("tk_correct"),objs.getString("tk_categoryID"),objs.getString("id")));
                            }
                            Exam(arrayList);

                        }catch (Exception e){
                        }
                    }
                });

        return view;
    }

    private void init(View view) {
        counter[0]=0;
        btnExamStart=view.findViewById(R.id.examStart);
        constExamScreen=view.findViewById(R.id.examScreen);
        constExamFinish=view.findViewById(R.id.examFinish);
        progressBar=view.findViewById(R.id.Activity_Question_Progresbar);
        btnAnswerA=view.findViewById(R.id.buttonAnswerA);
        btnAnswerB=view.findViewById(R.id.buttonAnswerB);
        btnAnswerC=view.findViewById(R.id.buttonAnswerC);
        btnAnswerD=view.findViewById(R.id.buttonAnswerD);
        txtCorrectAnswerCounter=view.findViewById(R.id.textViewCorrectAnswerCounter);
        txtWrongAnswerCounter=view.findViewById(R.id.textViewWrongAnswerCounter);

        txtQuestions=view.findViewById(R.id.textViewQuestion);
        txtQuestionsCounter=view.findViewById(R.id.textViewQuestionCounter);
    }
    private void Time() {
        int a = 0;
        final int totalTime = 100;
        t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;
                while (jumpTime < totalTime ) {

                    try {
                        sleep(12000);
                        jumpTime += 1;
                        progressBar.setProgress(jumpTime);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }
            }
        };

        t.start();

    }
    private void Exam(final ArrayList<Exam> arrayList){


        btnAnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestion(1,arrayList,counter[0]);
                counter[0]=counter[0]+1;
            }
        });


        btnAnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestion(2,arrayList,counter[0]);
                counter[0]=counter[0]+1;
            }
        });
        btnAnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestion(3,arrayList,counter[0]);
                counter[0]=counter[0]+1;
            }
        });
        btnAnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestion(4,arrayList,counter[0]);
                counter[0]=counter[0]+1;
            }
        });
    }

    private void addQuestion(int answer, ArrayList<Exam> arrayList, int counter) {


        if(counter==20){
            constExamScreen.setVisibility(View.GONE);
            constExamFinish.setVisibility(View.VISIBLE);
            int correctAnswer=0;
            for(AddQuestions item : arrayListAnswer){
                correctAnswer+=Integer.valueOf(item.getAnswerType());
            }
            txtWrongAnswerCounter.setText(""+correctAnswer);
            txtCorrectAnswerCounter.setText(""+(20-correctAnswer));
            Alagan.Instance.dbString
                    .put("command","startExam")
                    .put("userID","2")
                    .read(new AlaganStringDatabase.AlaganListener() {
                        @Override
                        public void onResponse(String response) {
                            for(AddQuestions item : arrayListAnswer){
                                Alagan.Instance.dbString
                                        .put("command","answer")
                                        .put("type",item.getAnswerType())
                                        .put("examID",response)
                                        .put("examID","2")
                                        .put("questionID",item.getQuestionID())
                                        .read(new AlaganStringDatabase.AlaganListener() {
                                            @Override
                                            public void onResponse(String response) {

                                            }
                                        });
                            }
                        }
                    });




        }else{
            String correcAnswerResult="0";
            if(answer==Integer.valueOf(arrayList.get(counter).getCorrectAnswer())){
                correcAnswerResult="1";
            }
            arrayListAnswer.add(new AddQuestions(correcAnswerResult,"1","2",arrayList.get(counter).getQuestionID()));
            txtQuestions.setText(""+arrayList.get(counter).getQuestions());
            txtQuestionsCounter.setText(""+(counter+1));
            btnAnswerA.setText(""+arrayList.get(counter).getAnswerA());
            btnAnswerB.setText(""+arrayList.get(counter).getAnswerB());
            btnAnswerC.setText(""+arrayList.get(counter).getAnswerC());
            btnAnswerD.setText(""+arrayList.get(counter).getAnswerD());
        }

    }


}
