package com.babic.expensetracker.business.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.babic.expensetracker.business.dao.contract.DatabaseContract;

public final class AppDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ExpenseIncomeDb.db";
    public static final int DATABASE_VERSION = 1;

    public AppDatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(DatabaseContract.ExpenseDataTable.SQL_CREATE_EXPENSE_DATA_TABLE);
        db.execSQL(DatabaseContract.IncomeDataTable.SQL_CREATE_EXPENSE_DATA_TABLE);
        db.execSQL(DatabaseContract.ExpenseTypeTable.SQL_CREATE_EXPSNSE_TYPE_TABLE);
//        db.execSQL(DatabaseContract.IncomeTypeTable.SQL_CREATE_EXPSNSE_TYPE_TABLE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

    }
}
