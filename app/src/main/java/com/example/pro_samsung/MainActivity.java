package com.example.pro_samsung;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pro_samsung.Room.DBClient;
import com.example.pro_samsung.forCheater.CheaterCabinetActivity;
import com.example.pro_samsung.forCheater.LogUpActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences shared;
    private EditText e_name,e_second_name,e_school,e_clas_s, login_for_teacher,password_for_teacher;
    public String name,second_name,school,clas_s;
    private Intent i;
    private boolean is_first_launch;
    private String login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        @SuppressLint("StaticFieldLeak")
        class UploadForTheFirstTimeQuestion extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                List<Question> questions = DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().getAll();
                if(!questions.isEmpty()){
                    for (Question question : questions) {
                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().delete(question);
                    }
                    questions.clear();
                }

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
               return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }
        final Button start = findViewById(R.id.var3);
        Button enter = findViewById(R.id.enter);
        Button log_up = findViewById(R.id.log_up);
        login_for_teacher =findViewById(R.id.login_for_teacher);
        password_for_teacher=findViewById(R.id.password_for_teacher);

        e_name = findViewById(R.id.name);
        e_second_name = findViewById(R.id.sec_name);
        e_school = findViewById(R.id.school);

        e_clas_s = findViewById(R.id.clas_s);
        loadSettings();
        if(is_first_launch){
            UploadForTheFirstTimeQuestion uq = new UploadForTheFirstTimeQuestion();
            uq.execute();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editor = shared.edit();

            findViewById(R.id.hint_for_cheater).setVisibility(View.VISIBLE);
            editor.putBoolean("is_first_launch", false);
            editor.apply();
            login = "test@test.ru";
            password = "test";

        } else {
            findViewById(R.id.hint_for_cheater).setVisibility(View.INVISIBLE);

        }


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = e_name.getText().toString();
                second_name = e_second_name.getText().toString();
                school = e_school.getText().toString();
                clas_s = e_clas_s.getText().toString();


                if(name.isEmpty() || second_name.isEmpty() || school.isEmpty() || clas_s.isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.EmptyErorr, Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString("name",name);
                    editor.putString("second_name",second_name);
                    editor.putString("school",school);
                    editor.putString("clas_s",clas_s);

                    editor.apply();
                    i = new Intent(MainActivity.this, TestActivity.class);
                    i.putExtra("name",name);
                    i.putExtra("second_name",second_name);
                    i.putExtra("school",school);
                    i.putExtra("clas_s",clas_s);
                    startActivity(i);
                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login_in = login_for_teacher.getText().toString().trim();
                String password_in  = password_for_teacher.getText().toString().trim();
                if(login_in.equals(login) && password_in.equals(password)){
                    Intent i = new Intent(MainActivity.this, CheaterCabinetActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.Failed_login, Toast.LENGTH_SHORT).show();

                }

            }
        });

        log_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogUpActivity.class));
            }
        });



    }

    public String getName() {
        return name;
    }

    public void loadSettings() {
        shared = getSharedPreferences("baseSettings", Context.MODE_PRIVATE);

        if(shared != null){
            is_first_launch = shared.getBoolean("is_first_launch", true);
            if(!is_first_launch){
                e_name.setText(shared.getString("name",""));
                e_second_name.setText(shared.getString("second_name",""));
                e_school.setText(shared.getString("school",""));
                e_clas_s.setText(shared.getString("clas_s",""));
                is_first_launch = shared.getBoolean("is_first_launch", true);
                login = shared.getString("login","test@test.ru");
                password = shared.getString("password","test");
                if(!shared.getString("name", "").equals("")){
                    Toast.makeText(this, "Привет, "+shared.getString("name","")+"!", Toast.LENGTH_SHORT).show();
                }
            }


        }

    }
}