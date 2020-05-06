package com.example.pro_samsung.forCheater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_samsung.MainActivity;
import com.example.pro_samsung.Question;
import com.example.pro_samsung.R;
import com.example.pro_samsung.Room.DBClient;

import java.util.ArrayList;
import java.util.List;

public class CheaterCabinetActivity extends AppCompatActivity{
    private List<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheater_layout);
        @SuppressLint("StaticFieldLeak")
        class GetQuestions extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                questions = DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().getAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

        }
        GetQuestions gt = new GetQuestions();
        gt.execute();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RecyclerView cheater_list = findViewById(R.id.cheat_list);
        cheater_list.setLayoutManager(new LinearLayoutManager(this));
        AdapterForCheater adapter = new AdapterForCheater(this, questions);
        cheater_list.setAdapter(adapter);
        Button null_question = findViewById(R.id.null_questions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        null_question.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                @SuppressLint("StaticFieldLeak")
                class NullQuestions extends AsyncTask<Void, Void, Void> {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        for (Question question : questions) {
                            DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().delete(question);
                        }
                        questions.clear();
                        Question q = new Question("Место, где река впадает в море, озеро, другую реку","исток","приток","русло","устье",4);
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                        q = new Question("Граница между бассейнами рек","русло","водораздел","речная система","устье",2);
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                        q = new Question("Углубление, в котором течёт река","русло","водораздел","речная система","устье",1);
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                        q = new Question("Наибольшее количество пресной воды на Земле сосредоточено в","реках","озерах","болотах","ледниках",1);
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                        q = new Question("Гидросфера является","воздушной оболочкой Земли","водной оболочкой Земли","земной корой","расплавленной мантией",2);
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                        q = new Question("Основной объем воды на Земле заключен:","в ледниках","в реках","в озерах","в водах Мирового океана",4);
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                        q = new Question("К водам суши относят:","заливы и проливы","реки и озера","моря и озера","моря и ледники",1);
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                        q = new Question("Какого питания реки не существует?","подземное","капельное","снеговое","грунтовое",3);
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                        questions = DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().getAll();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                    }

                }
                NullQuestions nq = new NullQuestions();
                nq.execute();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });
        Button add = findViewById(R.id.add_question);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CheaterCabinetActivity.this, AddQuestionActivity.class);
                finish();
                startActivity(intent);

            }
        });
        findViewById(R.id.protocol_uronit_vse_for_natasha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("baseSettings", Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CheaterCabinetActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {// взято с http://streletzcoder.ru/delaem-knopku-nazad-v-android-prilozhenii/
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(CheaterCabinetActivity.this, MainActivity.class);

            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
