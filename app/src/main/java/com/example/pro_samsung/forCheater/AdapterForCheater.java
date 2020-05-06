package com.example.pro_samsung.forCheater;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pro_samsung.Question;
import com.example.pro_samsung.R;

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
            Intent i = new Intent(c, DeleteQuestionActivity.class);
            i.putExtra(Question.class.getSimpleName(),question);
            c.startActivity(i);
        }
    }
}