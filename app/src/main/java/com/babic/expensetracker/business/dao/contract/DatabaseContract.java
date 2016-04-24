package com.babic.expensetracker.business.dao.contract;

import android.provider.BaseColumns;

public final class DatabaseContract {

    public static final String TYPE_PRIMARY_KEY = " INTEGER PRIMARY KEY";
    public static final String TYPE_PRIMARY_KEY_AUTOINCREMENT = " INTEGER PRIMARY KEY AUTOINCREMENT";

    private static final String SEPARATOR = ",";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";

    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_NUMERIC = " NUMERIC";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String TYPE_REAL = " REAL";
    private static final String TYPE_BOOLEAN = TYPE_INTEGER; //Quasi-type
    private static final String TYPE_BLOB = " BLOB";

    private DatabaseContract() {
        throw new AssertionError();
    }

    public static final class ExpenseDataTable implements BaseColumns {

        public static final String TABLE_NAME = "expense_data_table";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NAME_TYPE = TYPE_TEXT;

        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_AMOUNT_TYPE = TYPE_INTEGER;

        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DESCRIPTION_TYPE = TYPE_TEXT;

        public static final String COLUMN_CREATED_AT = "timestamp";
        public static final String COLUMN_CREATED_AT_TYPE = TYPE_NUMERIC;

        public static final String SQL_CREATE_EXPENSE_DATA_TABLE =
                CREATE_TABLE + TABLE_NAME + " (" +
                        _ID + TYPE_PRIMARY_KEY_AUTOINCREMENT + SEPARATOR +
                        COLUMN_NAME + COLUMN_NAME_TYPE + SEPARATOR +
                        COLUMN_AMOUNT + COLUMN_AMOUNT_TYPE + SEPARATOR +
                        COLUMN_DESCRIPTION + COLUMN_DESCRIPTION_TYPE + SEPARATOR +
                        COLUMN_CREATED_AT + COLUMN_CREATED_AT_TYPE + ")";

        public static final String[] ALL_COLUMNS = new String[]{
                COLUMN_NAME,
                COLUMN_AMOUNT,
                COLUMN_DESCRIPTION,
                COLUMN_CREATED_AT
        };
    }

    public static final class IncomeDataTable implements BaseColumns {

        public static final String TABLE_NAME = "income_data_table";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NAME_TYPE = TYPE_TEXT;

        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_AMOUNT_TYPE = TYPE_INTEGER;

        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DESCRIPTION_TYPE = TYPE_TEXT;

        public static final String COLUMN_CREATED_AT = "timestamp";
        public static final String COLUMN_CREATED_AT_TYPE = TYPE_NUMERIC;

        public static final String SQL_CREATE_EXPENSE_DATA_TABLE =
                CREATE_TABLE + TABLE_NAME + " (" +
                        _ID + TYPE_PRIMARY_KEY_AUTOINCREMENT + SEPARATOR +
                        COLUMN_NAME + COLUMN_NAME_TYPE + SEPARATOR +
                        COLUMN_AMOUNT + COLUMN_AMOUNT_TYPE + SEPARATOR +
                        COLUMN_DESCRIPTION + COLUMN_DESCRIPTION_TYPE + SEPARATOR +
                        COLUMN_CREATED_AT + COLUMN_CREATED_AT_TYPE + ")";

        public static final String[] ALL_COLUMNS = new String[]{
                COLUMN_NAME,
                COLUMN_AMOUNT,
                COLUMN_DESCRIPTION,
                COLUMN_CREATED_AT
        };
    }
}
