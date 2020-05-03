package com.example.pro_samsung;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;
import java.util.TooManyListenersException;

public class ResultActivity extends AppCompatActivity {




    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);
        Bundle arguments = getIntent().getExtras();

        assert arguments != null;
        String name = Objects.requireNonNull(arguments.get("name")).toString();
        String sec_name = Objects.requireNonNull(arguments.get("second_name")).toString();
        String school = Objects.requireNonNull(arguments.get("school")).toString();
        String clas_s = Objects.requireNonNull(arguments.get("clas_s")).toString();
        int mark = Integer.parseInt(Objects.requireNonNull(arguments.get("mark")).toString());

        TextView hi = findViewById(R.id.hi);
        TextView m = findViewById(R.id.mark);
        hi.setText("-\t "+name+" "+sec_name + "\n-\t учащийся(аяся) в школе " + school + "\n-\t из класса " + clas_s +  "\n-\t получил(а) оценку");
        m.setText(String.valueOf(mark));
        Button ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("finish", true);
                startActivity(intent);


            }
        });


    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Нельзя перепройти тест!", Toast.LENGTH_LONG).show();
    }




}