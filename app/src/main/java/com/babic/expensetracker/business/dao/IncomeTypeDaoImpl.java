package com.babic.expensetracker.business.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.babic.expensetracker.business.dao.contract.DatabaseContract.IncomeDataTable;
import com.babic.expensetracker.business.dao.contract.DatabaseContract.IncomeTypeTable;
import com.babic.expensetracker.business.data.IncomeType;
import com.babic.expensetracker.util.CursorUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class IncomeTypeDaoImpl extends BaseDao implements IncomeTypeDao {

    public IncomeTypeDaoImpl(AppDatabaseHelper databaseHelper, CursorUtils cursorUtils) {
        super(databaseHelper, cursorUtils);
    }

    @Override
    public void insert(final IncomeType type) {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.insert(IncomeDataTable.TABLE_NAME,
                                      null,
                                      incomeTypeToContentValue(type));
            }
        });
    }

    private ContentValues incomeTypeToContentValue(final IncomeType incomeType) {
        final ContentValues values = new ContentValues();

        values.put(IncomeTypeTable.COLUMN_NAME, incomeType.name);
        values.put(IncomeTypeTable.COLUMN_USAGE_COUNT, incomeType.usageCount);

        return values;
    }

    @Override
    public List<IncomeType> listIncomeTypes() {
        return readFromDatabase(databaseHelper, new ManagedFunction<List<IncomeType>>() {
            @Override
            public List<IncomeType> call(final SQLiteDatabase sqLiteDatabase) {
                return cursorToList(sqLiteDatabase.query(IncomeTypeTable.TABLE_NAME,
                                                         IncomeDataTable.ALL_COLUMNS,
                                                         null,
                                                         null,
                                                         null,
                                                         null,
                                                         null));
            }
        });
    }

    private IncomeType cursorToIncomeType(final Cursor cursor) {
        return new IncomeType(cursorUtils.getIntOrDefault(cursor, IncomeTypeTable._ID, Integer.MIN_VALUE),
                              cursorUtils.getStringOrDefault(cursor, IncomeTypeTable.COLUMN_NAME, ""),
                              cursorUtils.getIntOrDefault(cursor, IncomeTypeTable.COLUMN_USAGE_COUNT, 0));
    }

    private List<IncomeType> cursorToList(final Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            final List<IncomeType> data = new ArrayList<>(cursor.getCount());
            do {
                data.add(cursorToIncomeType(cursor));
            } while (cursor.moveToNext());

            return data;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteAll() {
        writeToDatabase(databaseHelper, new ManagedAction() {
            @Override
            public void call(final SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.delete(IncomeTypeTable.TABLE_NAME,
                                      null,
                                      null);
            }
        });
    }
}
