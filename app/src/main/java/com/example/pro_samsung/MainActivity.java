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
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences shared;
    private EditText e_name,e_second_name,e_school,e_clas_s,email_for_teacher,password_for_teacher;
    public String name,second_name,school,clas_s;
    private Intent i;
    private boolean is_first_launch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (getIntent().getBooleanExtra("finish", false)) finish();


        @SuppressLint("StaticFieldLeak")
        class UploadForTheFirstTimeQuestion extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
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
        Button start = findViewById(R.id.var3);
        Button enter = findViewById(R.id.enter);
        email_for_teacher=findViewById(R.id.email_for_teacher);
        password_for_teacher=findViewById(R.id.password_for_teacher);

        e_name = findViewById(R.id.name);
        e_second_name = findViewById(R.id.sec_name);
        e_school = findViewById(R.id.school);

        e_clas_s = findViewById(R.id.clas_s);
        loadSettings();
        if(is_first_launch){
            UploadForTheFirstTimeQuestion uq = new UploadForTheFirstTimeQuestion();
            uq.execute();
            Toast.makeText(this, "Это первый запуск. Стандартные вопросы загружены", Toast.LENGTH_LONG).show();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = e_name.getText().toString();
                second_name = e_second_name.getText().toString();
                school = e_school.getText().toString();
                clas_s = e_clas_s.getText().toString();


                if(name.isEmpty() || second_name.isEmpty() || school.isEmpty() || clas_s.isEmpty()){
                    Snackbar.make(view, "Заполните все поля", Snackbar.LENGTH_LONG).show();

                }else {
                    shared = getSharedPreferences("baseSettings",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString("name",name);
                    editor.putString("second_name",second_name);
                    editor.putString("school",school);
                    editor.putString("clas_s",clas_s);
                    editor.putBoolean("is_first_launch", false);
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
                String email = email_for_teacher.getText().toString().trim();
                String password = password_for_teacher.getText().toString().trim();
                if(email.equals("test@test.ru") && password.equals("test")){
                    Intent i = new Intent(MainActivity.this, CheaterCabinetActivity.class);
                    startActivity(i);

                } else {
                    Snackbar.make(view, "Пароль или логин неверный!", Snackbar.LENGTH_LONG).show();

                }

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
                if(!shared.getString("name", "").equals("")){
                    Toast.makeText(this, "Привет, "+shared.getString("name","")+"!", Toast.LENGTH_SHORT).show();
                }
            }


        } else {
            Toast.makeText(this,"Ничего не сохранено",Toast.LENGTH_LONG).show();
        }

    }
}