package com.tutkal.yazilimsinama.Screen.Fragment.Student;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tutkal.yazilimsinama.R;
import com.tutkal.yazilimsinama.Red.Red;
import com.tutkal.yazilimsinama.model.Alagan.Alagan;
import com.tutkal.yazilimsinama.model.Alagan.Class.AlaganStringDatabase;
import com.tutkal.yazilimsinama.model.Class.Category;
import com.tutkal.yazilimsinama.model.Class.DataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {


    private LineChartView lineChartView;
    private BarChart barChart;
    private RadioGroup radioGroup;
    private ConstraintLayout constOneExamScreen,constMultiExamScreen;
    private Spinner spinOneExam;
    private   int[] yAxisData = new int[5];
    String[] axisData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_student_statistics, container, false);
        init(view);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radiobtnOneExam:
                        constOneExamScreen.setVisibility(View.VISIBLE);
                        constMultiExamScreen.setVisibility(View.GONE);
                        oneExamStatistics();
                        break;
                    case R.id.radioBtnMultiExam:
                        constOneExamScreen.setVisibility(View.GONE);
                        constMultiExamScreen.setVisibility(View.VISIBLE);
                        multiExamStatistics();
                        break;

                }
            }
        });



        return view;
    }

    private void init(View view) {

        lineChartView = view.findViewById(R.id.chart);
        radioGroup=view.findViewById(R.id.radioGroup);
        constMultiExamScreen=view.findViewById(R.id.constMultiExam);
        constOneExamScreen=view.findViewById(R.id.constOneExam);
        barChart=view.findViewById(R.id.barchart);
        spinOneExam=view.findViewById(R.id.spinnerOneExam);
        new Red(getContext());

        //Red.getInstance().getViews().Spinner(getContext(),spinOneExam,"kategori1","kategori2");
    }

    private void multiExamStatistics(){

        final List<DataModel> arraylist=new ArrayList<>();
        Alagan.Instance.dbString
                .put("command","generalAllExam")
                .put("userID","2")
                .read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.e("aaaaaaaaa",response);

                            JSONArray jsonArray = new JSONArray(response);
                            axisData = new String[jsonArray.length()];
                            for (int i = 0; i<jsonArray.length();i++)
                            {
                                axisData[i]=(1+i)+" Sınav";

                                JSONObject obj=jsonArray.getJSONObject(i);
                                DataModel veri=new DataModel();
                                veri.date=obj.getString("tk_date");
                                veri.userID=obj.getString("tk_userID");
                                JSONArray categoryArray=obj.getJSONArray("category");
                                for (int k = 0; k <categoryArray.length() ; k++) {
                                    JSONObject categortData=categoryArray.getJSONObject(k);
                                    Category category=new Category();
                                    category.names=categortData.getString("tk_name");
                                    category.wrong=categortData.getString("D");
                                    category.correct=categortData.getString("Y");
                                    veri.cotegories.add(category);
                                }
                                arraylist.add(veri);
                            }

                        }catch (Exception e){

                        }




                        int a=0;


                        for (DataModel model: arraylist) {
                            float toplam=0;
                            float D=0,Y=0;
                            for(Category item: arraylist.get(a).cotegories){
                                D+=Integer.parseInt(item.correct);
                                Y+=Integer.parseInt(item.wrong);



                            }
                            //Toast.makeText(getContext(), ""+D, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getContext(), ""+Y, Toast.LENGTH_SHORT).show();
                            toplam=(D/(D+Y))*100;
                            //Toast.makeText(getContext(), ""+toplam, Toast.LENGTH_SHORT).show();
                            yAxisData[a] =(int)toplam;
                            a=a+1;
                        }






                        List yAxisValues = new ArrayList();
                        List axisValues = new ArrayList();


                        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

                        for (int i = 0; i < axisData.length; i++) {
                            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
                        }

                        for (int i = 0; i < yAxisData.length; i++) {
                            yAxisValues.add(new PointValue(i, yAxisData[i]));
                        }

                        List lines = new ArrayList();
                        lines.add(line);

                        LineChartData data = new LineChartData();
                        data.setLines(lines);
                        line.setColor(Color.parseColor("#f8833e"));
                        Axis axis = new Axis();
                        axis.setValues(axisValues);
                        axis.setTextSize(12);
                        axis.setTextColor(Color.parseColor("#999797"));
                        data.setAxisXBottom(axis);

                        Axis yAxis = new Axis();

                        yAxis.setTextColor(Color.parseColor("#999797"));
                        yAxis.setTextSize(12);
                        data.setAxisYLeft(yAxis);

                        lineChartView.setLineChartData(data);
                        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
                        viewport.top = 100;

                        lineChartView.setLineChartData(data);
                        Viewport viewport2 = new Viewport(lineChartView.getMaximumViewport());

                        lineChartView.setMaximumViewport(viewport);
                        lineChartView.setCurrentViewport(viewport2);
                    }
                });




    }

    private void oneExamStatistics(){

        final List<DataModel> arraylist=new ArrayList<>();
        Alagan.Instance.dbString
                .put("command","generalAllExam")
                .put("userID","2")
                .read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            ArrayList<String> spinnerlist=new ArrayList<>();
                            for (int i = 0; i<jsonArray.length();i++)
                            {
                                spinnerlist.add((i+1)+" Sınav");
                                JSONObject obj=jsonArray.getJSONObject(i);
                                DataModel veri=new DataModel();
                                veri.date=obj.getString("tk_date");
                                veri.userID=obj.getString("tk_userID");
                                JSONArray categoryArray=obj.getJSONArray("category");
                                for (int k = 0; k <categoryArray.length() ; k++) {
                                    JSONObject categortData=categoryArray.getJSONObject(k);
                                    Category category=new Category();
                                    category.names=categortData.getString("tk_name");
                                    category.wrong=categortData.getString("D");
                                    category.correct=categortData.getString("Y");
                                    veri.cotegories.add(category);
                                }
                                arraylist.add(veri);


                            }
                            Red.getInstance().getViews().Spinner(getContext(),spinOneExam,spinnerlist);

                            spinOneExam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    float[] cat1=new float[5];
                                    int counter=0;
                                    for(Category item: arraylist.get(i).cotegories){
                                        cat1[counter]=(Float.parseFloat(item.correct)/(Float.parseFloat(item.correct)+Float.parseFloat(item.wrong)))*100;
                                        //Toast.makeText(getContext(), ""+ cat1[counter]+item.correct+item.wrong, Toast.LENGTH_SHORT).show();
                                        counter=counter+1;
                                    }


                                    ArrayList<BarEntry> entries = new ArrayList<>();
                                    entries.add(new BarEntry( cat1[0], 0));
                                    entries.add(new BarEntry( cat1[1], 1));
                                    entries.add(new BarEntry( cat1[2], 2));
                                    entries.add(new BarEntry( cat1[3], 3));
                                    entries.add(new BarEntry( cat1[4], 4));
                                    //entries.add(new BarEntry(19f, 5));

                                    BarDataSet bardataset = new BarDataSet(entries, "Cells");

                                    ArrayList<String> labels = new ArrayList<String>();
                                    labels.add("Tarih");
                                    labels.add("Coğrafya");
                                    labels.add("Genel Kültür");
                                    labels.add("Spor");
                                    labels.add("Matematik");


                                    BarData data = new BarData(labels, bardataset);
                                    barChart.setData(data); // set the data and list of labels into chart
                                    barChart.setDescription("İstatistik");  // set the description
                                    bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                    barChart.animateY(5000);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                        }catch (Exception e){

                        }
                    }
                });



    }

}


