package com.babic.expensetracker.injection.module;

import android.content.Context;

import com.babic.expensetracker.application.ExpenseTrackerApplication;
import com.babic.expensetracker.injection.qualifier.ForApplication;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final ExpenseTrackerApplication application;

    public ApplicationModule(ExpenseTrackerApplication application) {
        this.application = application;
    }

    @Provides
    @ForApplication
    public Context provideApplicationContext() {
        return application;
    }

    public interface Exposes {

        @ForApplication
        ExpenseTrackerApplication expenseTrackerApplication();
    }
}
