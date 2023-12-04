package com.room.roomapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.room.roomapp.model.ListaProduto;
import com.room.roomapp.model.Produto;

import java.util.List;

@Dao
public interface ProdutoDao {
    @Query("SELECT * FROM lista")
    public List<ListaProduto> getAllListaProduto();

    @Query("SELECT * FROM produto")
    public List<Produto> getAll();

    @Query("Select * FROM produto WHERE id==:id")
    public Produto getById(long id);

    @Insert
    public long insert(Produto produto);

    @Delete
    public void delete(Produto produto);

    @Update
    public void update(Produto produto);
}
