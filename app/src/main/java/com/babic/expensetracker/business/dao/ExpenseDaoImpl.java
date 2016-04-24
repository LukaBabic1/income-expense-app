package com.babic.expensetracker.business.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.babic.expensetracker.business.dao.contract.DatabaseContract.ExpenseDataTable;
import com.babic.expensetracker.business.data.ExpenseData;
import com.babic.expensetracker.util.CursorUtils;

import java.util.ArrayList;
import java.util.List;

public final class ExpenseDaoImpl extends BaseDao implements ExpenseDao {

    public ExpenseDaoImpl(AppDatabaseHelper appDatabaseHelper, CursorUtils cursorUtils) {
        super(appDatabaseHelper, cursorUtils);
    }

    @Override
    public void saveExpense(final ExpenseData data) {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase database) {
                database.insert(ExpenseDataTable.TABLE_NAME, null, expenseDataToContentValues(data));
            }
        });
    }

    private ContentValues expenseDataToContentValues(final ExpenseData data) {
        final ContentValues values = new ContentValues();

        values.put(ExpenseDataTable.COLUMN_NAME, data.name);
        values.put(ExpenseDataTable.COLUMN_AMOUNT, data.amount);
        values.put(ExpenseDataTable.COLUMN_DESCRIPTION, data.description);
        values.put(ExpenseDataTable.COLUMN_CREATED_AT, data.timestamp);

        return values;
    }

    @Override
    public ExpenseData getExpenseById(final String id) {
        return readFromDatabase(databaseHelper, new ManagedFunction<ExpenseData>() {
            @Override
            public ExpenseData call(final SQLiteDatabase database) {
                return cursorToExpenseData(database.query(ExpenseDataTable.TABLE_NAME,
                                                          ExpenseDataTable.ALL_COLUMNS,
                                                          ExpenseDataTable._ID + " = ?",
                                                          new String[]{id},
                                                          null,
                                                          null,
                                                          null)
                );
            }
        });
    }

    private ExpenseData cursorToExpenseData(final Cursor cursor) {
        return new ExpenseData(cursorUtils.getIntOrDefault(cursor, ExpenseDataTable._ID, 0),
                               cursorUtils.getStringOrDefault(cursor, ExpenseDataTable.COLUMN_NAME, ""),
                               cursorUtils.getStringOrDefault(cursor, ExpenseDataTable.COLUMN_DESCRIPTION, ""),
                               cursorUtils.getIntOrDefault(cursor, ExpenseDataTable.COLUMN_AMOUNT, 0),
                               cursorUtils.getLongOrDefault(cursor, ExpenseDataTable.COLUMN_CREATED_AT, 0L));
    }

    @Override
    public List<ExpenseData> getAllExpenses() {
        return readFromDatabase(databaseHelper, new ManagedFunction<List<ExpenseData>>() {
            @Override
            public List<ExpenseData> call(final SQLiteDatabase database) {
                return cursorToList(database.query(ExpenseDataTable.TABLE_NAME,
                                                   ExpenseDataTable.ALL_COLUMNS,
                                                   null,
                                                   null,
                                                   null,
                                                   null,
                                                   null)
                );
            }
        });
    }

    private List<ExpenseData> cursorToList(final Cursor cursor) {
        final List<ExpenseData> data = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                data.add(cursorToExpenseData(cursor));
            } while (cursor.moveToNext());
        }

        return data;
    }

    @Override
    public List<ExpenseData> getExpenseFromInterval(final long start, final long end) {
        return null;
    }

    @Override
    public void deleteAll() {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase database) {
                database.delete(ExpenseDataTable.TABLE_NAME, null, null);
            }
        });
    }
}
