package com.example.pro_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestActivity extends AppCompatActivity {
    private TextView Ex_name;
    private Button var1;
    private Button var2;
    private Button var3;
    private Button var4;

    private List<Question> questions = new ArrayList<>();

    private int num_of_ex = 5;

    private int [] correct_answer_or_not = new  int [num_of_ex];
    private int i = 0;
    String name ;
    String sec_name;
    String school;
    String clas_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        class GetQuestions extends AsyncTask<Void, Void, List<Question>> {

            @Override
            protected List<Question> doInBackground(Void... voids) {
                List<Question> i = DBClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase().questionDao()
                        .getAll();
                questions = DBClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase().questionDao()
                        .getAll();

                Collections.shuffle(questions);
                List<Question> all_questions = new ArrayList<>(questions);
                questions.clear();
                for (int i1 = 0; i1 < 5; i1++) {
                    questions.add(all_questions.get(i1));
                }
                return i;
            }

            @Override
            protected void onPostExecute(List<Question> questions2) {
                super.onPostExecute(questions2);
            }

        }
        GetQuestions gt = new GetQuestions();
        gt.execute();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bundle arguments = getIntent().getExtras();
        name = arguments.get("name").toString();
        sec_name = arguments.get("second_name").toString();
        school = arguments.get("school").toString();
        clas_s = arguments.get("clas_s").toString();

        TextView TextStudent = findViewById(R.id.Name);
        TextStudent.setText("   Привет, "+name+" "+sec_name + " учащийся(яся) в школе " + school + " из класса " + clas_s +  "!");

        Ex_name =  findViewById(R.id.Ex_name);
        var1 =  findViewById(R.id.var1);
        var2=  findViewById(R.id.var2);
        var3 =  findViewById(R.id.var3);
        var4 =  findViewById(R.id.var4);


        var1.setOnClickListener(onClickListener);
        var2.setOnClickListener(onClickListener2);
        var3.setOnClickListener(onClickListener3);
        var4.setOnClickListener(onClickListener4);



if(questions.size()!=0){
    update_texts();

}else {
    Ex_name.setText("всё плохо");
}


    }
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(1 == questions.get(i).getAnswer()){
                correct_answer_or_not[i] = 1;
            }
            if(i==num_of_ex-1){
                //Ex_name.setText(String.valueOf(show_result()));
                show_result();
            }else {
                i++;
                update_texts();
            }

        }
    };
    private final View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(2 == questions.get(i).getAnswer()){
                correct_answer_or_not[i] = 1;
            }
            if(i==num_of_ex-1){
                show_result();
            }else {
                i++;
                update_texts();

            }
        }
    };
    private final View.OnClickListener onClickListener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(3 == questions.get(i).getAnswer()){
                correct_answer_or_not[i] = 1;
            }
            if(i==num_of_ex-1){
                show_result();
            }else {
                i++;
                update_texts();

            }
        }
    };
    private final View.OnClickListener onClickListener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(4 == questions.get(i).getAnswer()){
                correct_answer_or_not[i] = 1;
            }
            if(i==num_of_ex-1){
                show_result();
            }else {
                i++;
                update_texts();

            }
        }
    };
    private void show_result() {
        int num_of_cor_ansver = 0;
        for (int i = 0; i < num_of_ex; i++) {
            if (correct_answer_or_not[i] == 1) {
                num_of_cor_ansver++;
            }
        }
        if(num_of_cor_ansver<2){
            num_of_cor_ansver = 2;
        }
        Intent i = new Intent(TestActivity.this, ResultActivity.class);
        i.putExtra("name",name);
        i.putExtra("second_name",sec_name);
        i.putExtra("school",school);
        i.putExtra("clas_s",clas_s);
        i.putExtra("mark", num_of_cor_ansver) ;
        startActivity(i);

    }
    private void update_texts() {
        Ex_name.setText(questions.get(i).getQuestion());
        var1.setText(questions.get(i).getVariant1());
        var2.setText(questions.get(i).getVariant2());
        var3.setText(questions.get(i).getVariant3());
        var4.setText(questions.get(i).getVariant4());
    }

}