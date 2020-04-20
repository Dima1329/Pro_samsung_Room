package com.example.pro_samsung;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
}
