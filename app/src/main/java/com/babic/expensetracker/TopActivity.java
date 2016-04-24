package com.babic.expensetracker;

import android.support.v7.app.AppCompatActivity;

import com.babic.expensetracker.application.ExpenseTrackerApplication;

public abstract class TopActivity extends AppCompatActivity {

    protected final ExpenseTrackerApplication getApplicationInstance() {
        return ExpenseTrackerApplication.from(this);
    }
}
