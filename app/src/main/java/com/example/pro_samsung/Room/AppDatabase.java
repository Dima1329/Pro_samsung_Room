package com.example.pro_samsung.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pro_samsung.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
}
