package com.example.pro_samsung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pro_samsung.Room.DBClient;

import java.io.Serializable;

public class DeleteQuestionActivity extends AppCompatActivity{
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_question);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            question = (Question) arguments.getSerializable(Question.class.getSimpleName());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
                "Вопрос: " + question.getQuestion() + "\n" +
                        "Первый вариант ответа: " + question.getVariant1() + "\n" +
                        "Второй вариант ответа: " + question.getVariant2() + "\n" +
                        "Третий вариант ответа: " + question.getVariant3() + "\n" +
                        "Четвёртый вариант ответа: " + question.getVariant4() + "\n" +
                        "Номер правильного варианта ответа: " + question.getAnswer() + "\n");
        builder.setCancelable(false);
        builder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent i = new Intent(DeleteQuestionActivity.this, CheaterCabinetActivity.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Удалить вопорс", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                class DeleteQuestion extends AsyncTask<Void, Void, Void> {

                    @Override
                    protected Void doInBackground(Void... voids) {

                        DBClient.getInstance(getApplicationContext()).getAppDatabase().questionDao().delete(question);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                    }
                }
                DeleteQuestion uq = new DeleteQuestion();
                uq.execute();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Вопрос удалён!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DeleteQuestionActivity.this, CheaterCabinetActivity.class);
                startActivity(i);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }
}
