package com.example.pro_samsung.forCheater;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pro_samsung.Question;
import com.example.pro_samsung.R;
import com.example.pro_samsung.Room.DBClient;

public class AddQuestionActivity extends AppCompatActivity {
    String question;
    String v1;
    String v2;
    String v3 ;
    String v4;
    int ans;
    Question question_q;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_question);
        Button ok = findViewById(R.id.ok_button);
        final EditText q = findViewById(R.id.question_e);
        final EditText v1_e = findViewById(R.id.var_first_e);
        final EditText v2_e = findViewById(R.id.var_sec_e);
        final EditText v3_e = findViewById(R.id.var_third_e);
        final EditText v4_e = findViewById(R.id.var_fourth_e);
        final EditText ans_e = findViewById(R.id.answer_e);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question = q.getText().toString().trim();
                v1 = v1_e.getText().toString().trim();
                v2 = v2_e.getText().toString().trim();
                v3 = v3_e.getText().toString().trim();
                v4 = v4_e.getText().toString().trim();

                if(question.isEmpty()|| v1.isEmpty()|| v2.isEmpty()|| v3.isEmpty()|| v4.isEmpty()|| ans_e.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Заполните все поля!", Toast.LENGTH_LONG).show();
                } else {
                    ans = Integer.parseInt(ans_e.getText().toString().trim());

                    if (ans<5 && ans > 0){
                        question_q=new Question(question,v1,v2,v3,v4,ans);
                        @SuppressLint("StaticFieldLeak")
                        class AddQuestion extends AsyncTask<Void, Void, Void> {

                            @Override
                            protected Void doInBackground(Void... voids) {

                                DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(question_q);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                            }
                        }
                        AddQuestion aq = new AddQuestion();
                        aq.execute();

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent i = new Intent(AddQuestionActivity.this, CheaterCabinetActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(),"Ответом должна быть цифра от 1 до 4!", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
        Button cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddQuestionActivity.this, CheaterCabinetActivity.class);
                startActivity(i);
            }
        });

    }
}
