package com.example.pro_samsung;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        }

        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setMessage(question.getQuestion() +"/n"+
                    question.getQuestion()+"/n"+
                    question.getVariant1() +"/n"+
                    question.getVariant2() +"/n"+
                    question.getVariant3() +"/n"+
                    question.getVariant4() +"/n"+
                    question.getAnswer() +"/n")
                    .setCancelable(false)
                    .setNegativeButton("Хорошо", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            Toast.makeText(c, "Нажат", Toast.LENGTH_LONG).show();
        }
    }
}