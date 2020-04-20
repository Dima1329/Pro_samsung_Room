package com.example.pro_samsung;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {




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
        hi.setText("    "+name+" "+sec_name + " учащийся(яся) в школе " + school + " из класса " + clas_s +  " получил(а) оценку");
        m.setText(String.valueOf(mark));


    }




}