package com.example.pro_samsung;

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
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences shared;
    private EditText e_name,e_second_name,e_school,e_clas_s;
    public String name,second_name,school,clas_s;
    public QuestionDao questionDao;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").build();
        questionDao = db.questionDao();



        Button start = findViewById(R.id.var3);
        e_name = findViewById(R.id.name);
        e_second_name = findViewById(R.id.sec_name);
        e_school = findViewById(R.id.school);
        
        e_clas_s = findViewById(R.id.clas_s);

        loadSettings();

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
                    editor.apply();
                    i = new Intent(MainActivity.this, TestActivity.class);
                    i.putExtra("name",name);
                    i.putExtra("second_name",second_name);
                    i.putExtra("school",school);
                    i.putExtra("clas_s",clas_s);
                    class SaveTask extends AsyncTask<Void, Void, Void> {

                        @Override
                        protected Void doInBackground(Void... voids) {

                            Question q = new Question("    Место, где река впадает в море, озеро, другую реку","исток","приток","русло","устье",4);

                            DBClient.getInstance(getApplicationContext()).getAppDatabase()
                                    .questionDao()
                                    .insert(q);
                            q = new Question("    Граница между бассейнами рек","русло","водораздел","речная система","устье",2);
                            DBClient.getInstance(getApplicationContext()).getAppDatabase()
                                    .questionDao()
                                    .insert(q);
                            q = new Question("    Углубление, в котором течёт река","русло","водораздел","речная система","устье",1);
                            DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                            q = new Question("    Наибольшее количество пресной воды на Земле сосредоточено в","реках","озерах","болотах","ледниках",1);
                            DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                            q = new Question("    Гидросфера является","воздушной оболочкой Земли","водной оболочкой Земли","земной корой","расплавленной мантией",2);
                            DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                            q = new Question("    Основной объем воды на Земле заключен:","в ледниках","в реках","в озерах","в водах Мирового океана",4);
                            DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                            q = new Question("    К водам суши относят:","заливы и проливы","реки и озера","моря и озера","моря и ледники",1);
                            DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);
                            q = new Question("    Какого питания реки не существует?","подземное","капельное","снеговое","грунтовое",3);
                            DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().insert(q);

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            finish();

                            startActivity(i);

                        }
                    }

                    SaveTask st = new SaveTask();
                    st.execute();





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

            e_name.setText(shared.getString("name",""));
            e_second_name.setText(shared.getString("second_name",""));
            e_school.setText(shared.getString("school",""));
            e_clas_s.setText(shared.getString("clas_s",""));
            if(!shared.getString("name", "").equals("")){
                Toast.makeText(this, "Привет, "+shared.getString("name",""), Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this,"Ничего не сохранено",Toast.LENGTH_LONG).show();
        }

    }
}