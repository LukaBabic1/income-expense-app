package com.babic.expensetracker.injection.module;

import android.content.Context;

import com.babic.expensetracker.application.ExpenseTrackerApplication;
import com.babic.expensetracker.business.dao.AppDatabaseHelper;
import com.babic.expensetracker.business.dao.ExpenseDao;
import com.babic.expensetracker.business.dao.ExpenseDaoImpl;
import com.babic.expensetracker.injection.qualifier.ForApplication;
import com.babic.expensetracker.util.CursorUtilsImpl;

import javax.inject.Singleton;

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
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    public ExpenseDao provideExpenseDao(final AppDatabaseHelper appDatabaseHelper) {
        return new ExpenseDaoImpl(appDatabaseHelper, new CursorUtilsImpl());
    }

    @Provides
    @Singleton
    public AppDatabaseHelper provideAppDatabaseHelper(@ForApplication final Context context) {
        return new AppDatabaseHelper(context);
    }

    public interface Exposes {

        ExpenseDao expenseDao();
    }
}
