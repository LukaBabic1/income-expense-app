package com.babic.expensetracker.util;

import android.database.Cursor;

public interface CursorUtils {

    Long getLongOrDefault(final Cursor cursor, final String columnName, final long defaultValue);

    double getDoubleOrDefault(final Cursor cursor, final String columnName, final double defaultValue);

    float getFloatOrDefault(final Cursor cursor, final String columnName, final float defaultValue);

    int getIntOrDefault(final Cursor cursor, final String columnName, final int defaultValue);

    short getShortOrDefault(final Cursor cursor, final String columnName, final short defaultValue);

    String getStringOrDefault(final Cursor cursor, final String columnName, final String defaultValue);

    boolean getBooleanOrDefault(final Cursor cursor, final String columnName, final boolean defaultValue);
}
