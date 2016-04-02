package com.babic.expensetracker.injection.module;

import com.babic.expensetracker.application.ExpenseTrackerApplication;

import dagger.Module;

@Module
public final class ApplicationModule {

    private final ExpenseTrackerApplication application;

    public ApplicationModule(ExpenseTrackerApplication application) {
        this.application = application;
    }

    public interface Exposes {

    }
}
