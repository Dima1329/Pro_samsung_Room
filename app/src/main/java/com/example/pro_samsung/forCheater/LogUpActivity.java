package com.example.pro_samsung.forCheater;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pro_samsung.MainActivity;
import com.example.pro_samsung.R;

public class LogUpActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_up);
        final EditText old_login_e = findViewById(R.id.login_old);
        final EditText old_pass_e = findViewById(R.id.password_old);
        final EditText new_login_e =findViewById(R.id.login_new);
        final EditText new_pass_e = findViewById(R.id.password_new);
        final EditText check_pass_e =findViewById(R.id.password_new_check);
        Button cancel = findViewById(R.id.no);
        Button ok = findViewById(R.id.log_up_for_cheater);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LogUpActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String old_login = old_login_e.getText().toString().trim();
                String old_pass = old_pass_e.getText().toString().trim();
                String new_login = new_login_e.getText().toString().trim();
                String new_pass = new_pass_e.getText().toString().trim();
                String check_pass = check_pass_e.getText().toString().trim();

                if(old_login.isEmpty() || old_pass.isEmpty() || new_login.isEmpty() || new_pass.isEmpty() || check_pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.EmptyErorr, Toast.LENGTH_LONG).show();
                } else {
                    if (!new_pass.equals(check_pass)){
                        Toast.makeText(getApplicationContext(), R.string.ErorrNonSamePasswords, Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences shared = getSharedPreferences("baseSettings", Context.MODE_PRIVATE);

                        assert shared != null;
                        String login = shared.getString("login","test@test.ru");
                        String password = shared.getString("password","test");
                        if(!old_login.equals(login) && !old_pass.equals(password)){
                            Toast.makeText(getApplicationContext(), R.string.Failed_login, Toast.LENGTH_LONG).show();

                        } else {
                            shared.edit().putString("login", new_login).putString("password", new_pass).apply();
                            startActivity(new Intent(LogUpActivity.this,MainActivity.class));
                        }
                    }
                }

            }
        });

    }
}
