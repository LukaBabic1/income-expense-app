package com.babic.expensetracker.application;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.babic.expensetracker.injection.ComponentFactory;
import com.babic.expensetracker.injection.component.ApplicationComponent;

import javax.inject.Inject;

public final class ExpenseTrackerApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Inject
    protected TelephonyManager telephonyManager;

    public static ExpenseTrackerApplication from(final Context context) {
        return (ExpenseTrackerApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        injectMe();
    }

    private void injectMe() {
        (applicationComponent = ComponentFactory.createApplicationComponent(this)).inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
