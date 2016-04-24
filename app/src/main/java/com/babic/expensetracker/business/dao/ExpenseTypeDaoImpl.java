package com.babic.expensetracker.business.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.babic.expensetracker.business.dao.contract.DatabaseContract.ExpenseTypeTable;
import com.babic.expensetracker.business.data.ExpenseType;
import com.babic.expensetracker.util.CursorUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExpenseTypeDaoImpl extends BaseDao implements ExpenseTypeDao {

    public ExpenseTypeDaoImpl(AppDatabaseHelper databaseHelper, CursorUtils cursorUtils) {
        super(databaseHelper, cursorUtils);
    }

    @Override
    public void insert(final ExpenseType type) {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.insert(ExpenseTypeTable.TABLE_NAME,
                                      null,
                                      expenseTypeToContentValues(type));
            }
        });
    }

    private ContentValues expenseTypeToContentValues(final ExpenseType type) {
        final ContentValues values = new ContentValues();

        values.put(ExpenseTypeTable.COLUMN_NAME, type.name);
        values.put(ExpenseTypeTable.COLUMN_USAGE_COUNT, type.name);

        return values;
    }

    @Override
    public List<ExpenseType> listExpenseTypes() {
        return readFromDatabase(databaseHelper, new ManagedFunction<List<ExpenseType>>() {
            @Override
            public List<ExpenseType> call(final SQLiteDatabase sqLiteDatabase) {
                return cursorToList(sqLiteDatabase.query(ExpenseTypeTable.TABLE_NAME,
                                                         ExpenseTypeTable.ALL_COLUMNS,
                                                         null,
                                                         null,
                                                         null,
                                                         null,
                                                         null));
            }
        });
    }

    private List<ExpenseType> cursorToList(final Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            final List<ExpenseType> list = new ArrayList<>(cursor.getCount());
            do {
                list.add(cursorToExpenseType(cursor));
            } while (cursor.moveToNext());

            return list;
        } else {
            return Collections.emptyList();
        }
    }

    private ExpenseType cursorToExpenseType(final Cursor cursor) {
        return new ExpenseType(cursorUtils.getIntOrDefault(cursor, ExpenseTypeTable._ID, Integer.MIN_VALUE),
                               cursorUtils.getStringOrDefault(cursor, ExpenseTypeTable.COLUMN_NAME, ""),
                               cursorUtils.getIntOrDefault(cursor, ExpenseTypeTable.COLUMN_USAGE_COUNT, 0));
    }

    @Override
    public void deleteAll() {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.delete(ExpenseTypeTable.TABLE_NAME,
                                      null,
                                      null);
            }
        });
    }
}
