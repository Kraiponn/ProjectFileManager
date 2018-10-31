package com.ksntechnology.projectfilemanager.products.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ksntechnology.projectfilemanager.products.db.dao.InventoryDao;
import com.ksntechnology.projectfilemanager.products.db.entity.DetailsEntity;
import com.ksntechnology.projectfilemanager.products.db.entity.ProductsEntity;

@Database(entities = {ProductsEntity.class, DetailsEntity.class}, version = 1)
public abstract class InventoryDB extends RoomDatabase {
    static String TAG = "InventoryDB";
    private static InventoryDB iInventoryDB;

    public abstract InventoryDao inventoryDao();

    public static InventoryDB newInstance(Context context) {
        String DB_NAME = "db-inventory";

        if (iInventoryDB == null) {
            iInventoryDB = Room.databaseBuilder(
                context,
                    InventoryDB.class,
                    DB_NAME
            ).build();
        }

        return iInventoryDB;
    }

    public static void onDestroyInstance() {
        iInventoryDB = null;
    }

}
