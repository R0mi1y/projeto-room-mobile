package com.room.roomapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.room.roomapp.model.Lista;
import com.room.roomapp.model.ListaProduto;

import java.util.List;

@Dao
public interface ListaDao {
    @Query("SELECT * FROM lista")
    public List<ListaProduto> getAllListaProduto();

    @Query("SELECT * FROM lista")
    public List<Lista> getAll();

    @Query("Select * FROM lista WHERE id==:id")
    public Lista getById(long id);

    @Insert
    public long insert(Lista lista);

    @Delete
    public void delete(Lista lista);

    @Update
    public void update(Lista lista);
}
