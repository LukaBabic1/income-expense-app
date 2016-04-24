package com.babic.expensetracker.injection;

import android.support.v7.app.AppCompatActivity;

import com.babic.expensetracker.application.ExpenseTrackerApplication;
import com.babic.expensetracker.injection.component.activity.ActivityComponent;
import com.babic.expensetracker.injection.component.activity.DaggerActivityComponent;
import com.babic.expensetracker.injection.component.application.ApplicationComponent;
import com.babic.expensetracker.injection.component.application.DaggerApplicationComponent;
import com.babic.expensetracker.injection.module.ActivityModule;
import com.babic.expensetracker.injection.module.ApplicationModule;
import com.babic.expensetracker.injection.module.ServiceModule;

public final class ComponentFactory {

    private ComponentFactory() {
        throw new AssertionError();
    }

    public static ApplicationComponent createApplicationComponent(final ExpenseTrackerApplication application) {
        return DaggerApplicationComponent.builder()
                                         .serviceModule(new ServiceModule(application))
                                         .applicationModule(new ApplicationModule(application))
                                         .build();
    }

    public static ActivityComponent createActivityComponent(final AppCompatActivity appCompatActivity, final ExpenseTrackerApplication application) {
        return DaggerActivityComponent.builder()
                                      .applicationComponent(application.getApplicationComponent())
                                      .activityModule(new ActivityModule(appCompatActivity))
                                      .build();
    }
}
