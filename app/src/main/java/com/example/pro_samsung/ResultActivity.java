package com.example.pro_samsung;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;
import java.util.TooManyListenersException;

public class ResultActivity extends AppCompatActivity {



    private Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);
        Bundle arguments = getIntent().getExtras();


        assert arguments != null;
        String name = arguments.get("name").toString();
        String sec_name = arguments.get("second_name").toString();
        String school = arguments.get("school").toString();
        String clas_s = arguments.get("clas_s").toString();
        int mark = Integer.parseInt(arguments.get("mark").toString());

        TextView hi = findViewById(R.id.hi);
        TextView m = findViewById(R.id.mark);
        hi.setText(String.format("-\t %s %s\n-\t учащийся(аяся) в школе %s\n-\t из класса %s\n-\t получил(а) оценку", name, sec_name, school, clas_s));
        m.setText(String.valueOf(mark));
        Button ok= findViewById(R.id.exit_activity);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);// https://ru.stackoverflow.com/questions/575190/Выход-из-android-приложения
            }
        });




    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Нельзя перепройти тест!", Toast.LENGTH_SHORT).show();
    }




}