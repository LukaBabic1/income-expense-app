package com.babic.expensetracker.util;

import android.database.Cursor;

public final class CursorUtilsImpl implements CursorUtils {

    public Long getLongOrDefault(final Cursor cursor, final String columnName, final long defaultValue) {

        final int columnIndex = cursor.getColumnIndex(columnName);

        if (columnIndex >= 0) {
            return cursor.getLong(columnIndex);
        } else {
            return defaultValue;
        }
    }

    public double getDoubleOrDefault(final Cursor cursor, final String columnName, final double defaultValue) {

        final int columnIndex = cursor.getColumnIndex(columnName);

        if (columnIndex >= 0) {
            return cursor.getDouble(columnIndex);
        } else {
            return defaultValue;
        }
    }

    public float getFloatOrDefault(final Cursor cursor, final String columnName, final float defaultValue) {

        final int columnIndex = cursor.getColumnIndex(columnName);

        if (columnIndex >= 0) {
            return cursor.getFloat(columnIndex);
        } else {
            return defaultValue;
        }
    }

    public int getIntOrDefault(final Cursor cursor, final String columnName, final int defaultValue) {

        final int columnIndex = cursor.getColumnIndex(columnName);

        if (columnIndex >= 0) {
            return cursor.getInt(columnIndex);
        } else {
            return defaultValue;
        }
    }

    public short getShortOrDefault(final Cursor cursor, final String columnName, final short defaultValue) {

        final int columnIndex = cursor.getColumnIndex(columnName);

        if (columnIndex >= 0) {
            return cursor.getShort(columnIndex);
        } else {
            return defaultValue;
        }
    }

    public String getStringOrDefault(final Cursor cursor, final String columnName, final String defaultValue) {

        final int columnIndex = cursor.getColumnIndex(columnName);

        if (columnIndex >= 0) {
            return cursor.getString(columnIndex);
        } else {
            return defaultValue;
        }
    }

    public boolean getBooleanOrDefault(final Cursor cursor, final String columnName, final boolean defaultValue) {

        final int columnIndex = cursor.getColumnIndex(columnName);

        if (columnIndex >= 0) {
            return cursor.getInt(columnIndex) == 1;
        } else {
            return defaultValue;
        }
    }
}
