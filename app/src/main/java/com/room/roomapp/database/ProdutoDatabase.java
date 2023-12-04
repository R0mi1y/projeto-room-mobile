package com.room.roomapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.room.roomapp.dao.ProdutoDao;
import com.room.roomapp.model.Lista;
import com.room.roomapp.model.Produto;

@Database(entities = {Lista.class, Produto.class}, version = 1)
public abstract class ProdutoDatabase extends RoomDatabase {
    public abstract ProdutoDao getDao();
    private static volatile ProdutoDatabase INSTANCE;

    public static ProdutoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProdutoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ProdutoDatabase.class, "meu_banco")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
