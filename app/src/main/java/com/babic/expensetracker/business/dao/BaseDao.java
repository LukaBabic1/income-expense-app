package com.babic.expensetracker.business.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.babic.expensetracker.util.function.Action1;
import com.babic.expensetracker.util.function.Func1;
import com.babic.expensetracker.util.CursorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Base data access object that should be extended by other DAO implementations.
 */
public abstract class BaseDao {

    private static final String TAG = BaseDao.class.getSimpleName();

    protected static final int INT_TRUE = 1;
    protected static final int INT_FALSE = 0;
    protected static final String ORDER_DESC = " desc";

    protected final AppDatabaseHelper databaseHelper;
    protected final CursorUtils cursorUtils;

    public BaseDao(AppDatabaseHelper databaseHelper, CursorUtils cursorUtils) {
        this.cursorUtils = cursorUtils;
        this.databaseHelper = databaseHelper;
    }

    protected <T> T writeToDatabase(final SQLiteOpenHelper databaseHelper, final ManagedFunction<T> managedFunction) {
        return writeToDatabase(databaseHelper, false, managedFunction);
    }

    protected <T> T writeToDatabase(final SQLiteOpenHelper databaseHelper, final boolean inTransaction, final ManagedFunction<T> managedFunction) {
        SQLiteDatabase database = null;
        try {
            database = databaseHelper.getWritableDatabase();
            if (inTransaction) {
                database.beginTransaction();
            }

            T result = managedFunction.call(database);

            if (inTransaction) {
                database.setTransactionSuccessful();
            }

            return result;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        } finally {
            managedFunction.release();

            if (database != null) {
                if (database.inTransaction()) {
                    database.endTransaction();
                }
            }
        }

        return null;
    }

    protected void writeToDatabase(final SQLiteOpenHelper databaseHelper, final ManagedAction managedAction) {
        writeToDatabase(databaseHelper, false, managedAction);
    }

    protected void writeToDatabase(final SQLiteOpenHelper databaseHelper, final boolean inTransaction, final ManagedAction managedAction) {
        SQLiteDatabase database = null;
        try {
            database = databaseHelper.getWritableDatabase();
            if (inTransaction) {
                database.beginTransaction();
            }

            managedAction.call(database);

            if (inTransaction) {
                database.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        } finally {
            managedAction.release();

            if (database != null) {
                if (database.inTransaction()) {
                    database.endTransaction();
                }
            }
        }
    }

    protected <T> T readFromDatabase(final SQLiteOpenHelper dbHelper, final ManagedFunction<T> managedFunction) {
        return readFromDatabase(dbHelper, false, managedFunction);
    }

    protected <T> T readFromDatabase(final SQLiteOpenHelper dbHelper, final boolean inTransaction, final ManagedFunction<T> managedFunction) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getReadableDatabase();
            if (inTransaction) {
                database.beginTransaction();
            }

            T result = managedFunction.call(database);

            if (inTransaction) {
                database.setTransactionSuccessful();
            }

            return result;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        } finally {
            managedFunction.release();

            if (database != null) {
                if (database.inTransaction()) {
                    database.endTransaction();
                }
            }
        }

        return null;
    }

    private abstract class ManagedCursorBlock {

        private final List<Cursor> cursors = new ArrayList<>(2); // We will rarely use more

        protected Cursor managedCursor(final Cursor cursor) {

            if (cursor != null) {
                cursors.add(cursor);
            }

            return cursor;
        }

        public void release() {
            for (final Cursor cursor : cursors) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }

            cursors.clear();
        }
    }

    public abstract class ManagedAction extends ManagedCursorBlock implements Action1<SQLiteDatabase> {
    }

    public abstract class ManagedFunction<T> extends ManagedCursorBlock implements Func1<SQLiteDatabase, T> {
    }
}
