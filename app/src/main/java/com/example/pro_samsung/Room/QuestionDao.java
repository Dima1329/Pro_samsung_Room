package com.example.pro_samsung.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pro_samsung.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question WHERE id = :id")
    Question getById(long id);

    @Insert
    void insert(Question employee);

    @Update
    void update(Question employee);

    @Delete
    void delete(Question employee);
}
