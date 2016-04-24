package com.babic.expensetracker.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.babic.expensetracker.application.ExpenseTrackerApplication;
import com.babic.expensetracker.injection.ComponentFactory;
import com.babic.expensetracker.injection.component.activity.ActivityComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createComponent();
    }

    protected void createComponent() {
        activityComponent = ComponentFactory.createActivityComponent(this, ExpenseTrackerApplication.from(this));
        inject(activityComponent);
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
