package com.room.roomapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Lista {
    @PrimaryKey(autoGenerate = true)
    long id;

    String nome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
