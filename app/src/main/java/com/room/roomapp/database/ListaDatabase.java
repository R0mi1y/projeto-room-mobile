package com.room.roomapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.room.roomapp.dao.ListaDao;
import com.room.roomapp.model.Lista;
import com.room.roomapp.model.Produto;

@Database(entities = {Lista.class, Produto.class}, version = 1)
public abstract class ListaDatabase extends RoomDatabase {
    public abstract ListaDao getDao();
    private static volatile ListaDatabase INSTANCE;

    public static ListaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ListaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ListaDatabase.class, "meu_banco")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
