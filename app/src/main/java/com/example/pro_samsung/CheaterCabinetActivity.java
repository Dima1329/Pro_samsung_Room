package com.example.pro_samsung;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheaterCabinetActivity extends AppCompatActivity{
    private List<Question> questions = new ArrayList<>();
    private RecyclerView cheater_list;
    private AdapterForCheater adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheater_layout);
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
        cheater_list = findViewById(R.id.cheat_list);
        cheater_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterForCheater(this,questions);
        cheater_list.setAdapter(adapter);

    }
}
