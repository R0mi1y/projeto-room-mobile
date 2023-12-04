package com.room.roomapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ListaProduto {
    @Embedded
    public Lista lista;

    @Relation(parentColumn = "id", entityColumn = "id_lista")
    public List<Produto> produtos;
}