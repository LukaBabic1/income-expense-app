package com.babic.expensetracker.business.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.babic.expensetracker.business.dao.contract.DatabaseContract.IncomeDataTable;
import com.babic.expensetracker.business.data.IncomeData;
import com.babic.expensetracker.util.CursorUtils;

import java.util.ArrayList;
import java.util.List;

public final class IncomeDaoImpl extends BaseDao implements IncomeDao {

    public IncomeDaoImpl(AppDatabaseHelper databaseHelper, CursorUtils cursorUtils) {
        super(databaseHelper, cursorUtils);
    }

    @Override
    public void saveIncome(final IncomeData data) {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.insert(IncomeDataTable.TABLE_NAME, null, incomeDataToContentValues(data));
            }
        });
    }

    private ContentValues incomeDataToContentValues(final IncomeData data) {
        final ContentValues values = new ContentValues();

        values.put(IncomeDataTable.COLUMN_NAME, data.name);
        values.put(IncomeDataTable.COLUMN_DESCRIPTION, data.description);
        values.put(IncomeDataTable.COLUMN_AMOUNT, data.amount);
        values.put(IncomeDataTable.COLUMN_CREATED_AT, data.timestamp);

        return values;
    }

    @Override
    public IncomeData getIncomeById(final String id) {
        return readFromDatabase(databaseHelper, new ManagedFunction<IncomeData>() {
            @Override
            public IncomeData call(final SQLiteDatabase database) {
                return cursorToExpenseData(database.query(IncomeDataTable.TABLE_NAME,
                                                          IncomeDataTable.ALL_COLUMNS,
                                                          IncomeDataTable._ID + " = ?",
                                                          new String[]{id},
                                                          null,
                                                          null,
                                                          null)
                );
            }
        });
    }

    private IncomeData cursorToExpenseData(final Cursor cursor) {
        return new IncomeData(cursorUtils.getIntOrDefault(cursor, IncomeDataTable._ID, 0),
                              cursorUtils.getStringOrDefault(cursor, IncomeDataTable.COLUMN_NAME, ""),
                              cursorUtils.getStringOrDefault(cursor, IncomeDataTable.COLUMN_DESCRIPTION, ""),
                              cursorUtils.getIntOrDefault(cursor, IncomeDataTable.COLUMN_AMOUNT, 0),
                              cursorUtils.getLongOrDefault(cursor, IncomeDataTable.COLUMN_CREATED_AT, 0L));
    }

    @Override
    public List<IncomeData> getAllIncomes() {
        return readFromDatabase(databaseHelper, new ManagedFunction<List<IncomeData>>() {
            @Override
            public List<IncomeData> call(final SQLiteDatabase sqLiteDatabase) {
                return cursorToList(sqLiteDatabase.query(IncomeDataTable.TABLE_NAME,
                                                         IncomeDataTable.ALL_COLUMNS,
                                                         null,
                                                         null,
                                                         null,
                                                         null,
                                                         null));
            }
        });
    }

    private List<IncomeData> cursorToList(final Cursor cursor) {
        final List<IncomeData> data = new ArrayList<>(cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                data.add(cursorToExpenseData(cursor));
            } while (cursor.moveToNext());
        }

        return data;
    }

    @Override
    public List<IncomeData> getIncomeFromInterval(final long start, final long end) {
        return readFromDatabase(databaseHelper, new ManagedFunction<List<IncomeData>>() {
            @Override
            public List<IncomeData> call(SQLiteDatabase sqLiteDatabase) {
                return cursorToList(sqLiteDatabase.query(IncomeDataTable.TABLE_NAME,
                                                         IncomeDataTable.ALL_COLUMNS,
                                                         IncomeDataTable.COLUMN_CREATED_AT + " > ? AND " + IncomeDataTable.TABLE_NAME + " < ?",
                                                         new String[]{String.valueOf(start), String.valueOf(end)},
                                                         null,
                                                         null,
                                                         null));
            }
        });
    }

    @Override
    public void deleteAll() {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.delete(IncomeDataTable.TABLE_NAME, null, null);
            }
        });
    }
}
