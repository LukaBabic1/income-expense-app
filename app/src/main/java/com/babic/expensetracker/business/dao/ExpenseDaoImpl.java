package com.babic.expensetracker.business.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.babic.expensetracker.business.dao.contract.DatabaseContract.ExpenseDataTable;
import com.babic.expensetracker.business.data.ExpenseData;
import com.babic.expensetracker.util.CursorUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExpenseDaoImpl extends BaseDao implements ExpenseDao {

    public ExpenseDaoImpl(AppDatabaseHelper appDatabaseHelper, CursorUtils cursorUtils) {
        super(appDatabaseHelper, cursorUtils);
    }

    @Override
    public void save(final ExpenseData data) {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase database) {
                database.insert(ExpenseDataTable.TABLE_NAME, null, expenseDataToContentValues(data));
            }
        });
    }

    @Override
    public void update(final ExpenseData data) {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.update(ExpenseDataTable.TABLE_NAME,
                                      expenseDataToContentValues(data),
                                      ExpenseDataTable._ID + " = ?",
                                      new String[]{String.valueOf(data.id)});
            }
        });
    }

    @Override
    public void delete(final int id) {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.delete(ExpenseDataTable.TABLE_NAME,
                                      ExpenseDataTable._ID + "= ?",
                                      new String[]{String.valueOf(id)});
            }
        });
    }

    private ContentValues expenseDataToContentValues(final ExpenseData data) {
        final ContentValues values = new ContentValues();

        if (data.id != Integer.MIN_VALUE) {
            values.put(ExpenseDataTable._ID, data.id);
        }
        values.put(ExpenseDataTable.COLUMN_NAME, data.name);
        values.put(ExpenseDataTable.COLUMN_AMOUNT, data.amount);
        values.put(ExpenseDataTable.COLUMN_DESCRIPTION, data.description);
        values.put(ExpenseDataTable.COLUMN_CREATED_AT, data.timestamp);

        return values;
    }

    @Override
    public ExpenseData getById(final int id) {
        return readFromDatabase(databaseHelper, new ManagedFunction<ExpenseData>() {
            @Override
            public ExpenseData call(final SQLiteDatabase database) {
                final Cursor cursor = database.query(ExpenseDataTable.TABLE_NAME,
                                                     ExpenseDataTable.ALL_COLUMNS,
                                                     ExpenseDataTable._ID + "= ?",
                                                     new String[]{String.valueOf(id)},
                                                     null,
                                                     null,
                                                     null);

                if (cursor.moveToFirst()) {
                    return cursorToExpenseData(cursor);
                } else {
                    return ExpenseData.EMPTY;
                }
            }
        });
    }

    private List<ExpenseData> cursorToList(final Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            final List<ExpenseData> data = new ArrayList<>(cursor.getCount());
            do {
                data.add(cursorToExpenseData(cursor));
            } while (cursor.moveToNext());
            return data;
        } else {
            return Collections.emptyList();
        }
    }

    private ExpenseData cursorToExpenseData(final Cursor cursor) {
        return new ExpenseData(cursorUtils.getIntOrDefault(cursor, ExpenseDataTable._ID, 0),
                               cursorUtils.getStringOrDefault(cursor, ExpenseDataTable.COLUMN_NAME, ""),
                               cursorUtils.getStringOrDefault(cursor, ExpenseDataTable.COLUMN_DESCRIPTION, ""),
                               cursorUtils.getIntOrDefault(cursor, ExpenseDataTable.COLUMN_AMOUNT, 0),
                               cursorUtils.getLongOrDefault(cursor, ExpenseDataTable.COLUMN_CREATED_AT, 0L));
    }

    @Override
    public List<ExpenseData> getAll() {
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
