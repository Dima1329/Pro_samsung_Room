package com.example.pro_samsung;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_samsung.Room.DBClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
public class AdapterForCheater extends RecyclerView.Adapter<AdapterForCheater.ViewHolder> {

    private List<Question> localList;
    private LayoutInflater localInflater;
    private Context c;
    //private ItemClickListener

    AdapterForCheater(Context c, List<Question> data) {
        this.localList = data;
        this.localInflater = LayoutInflater.from(c);
        this.c = c;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = localInflater.inflate(R.layout.recyclerview_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question q = localList.get(position);
        holder.tquestion.setText(q.getQuestion());
        holder.setQuestion(q);
    }

    @Override
    public int getItemCount() {
        return localList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tquestion;
        Question question;

        public void setQuestion(Question question) {
            this.question = question;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tquestion = itemView.findViewById(R.id.item);
            tquestion.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setMessage(
                    "Вопрос: "+question.getQuestion()+"\n"+
                    "Первый вариант ответа: "+question.getVariant1() +"\n"+
                    "Второй вариант ответа: "+question.getVariant2() +"\n"+
                    "Третий вариант ответа: "+question.getVariant3() +"\n"+
                    "Четвёртый вариант ответа: "+question.getVariant4() +"\n"+
                    "Номер правильного варианта ответа: "+question.getAnswer() +"\n")
                    .setCancelable(false)
                    .setPositiveButton("Ок",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Удалить вопрос", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            class DeleteQuestion extends AsyncTask<Void, Void, Void> {

                                @Override
                                protected Void doInBackground(Void... voids) {

                                    DBClient.getInstance(c).getAppDatabase().questionDao().delete(question);
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
                            Toast.makeText(c, "Вопрос удалён!", Toast.LENGTH_SHORT).show();

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}