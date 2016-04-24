package com.babic.expensetracker.injection.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.babic.expensetracker.business.dao.ExpenseDao;
import com.babic.expensetracker.injection.qualifier.ForActivity;
import com.babic.expensetracker.injection.scope.ActivityScope;
import com.babic.expensetracker.ui.ExpenseAdapter;
import com.babic.expensetracker.ui.presenter.MainPresenterImpl;
import com.babic.expensetracker.ui.presenter.MainScreenContract.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @ForActivity
    public Context provideContext() {
        return appCompatActivity;
    }

    @Provides
    @ActivityScope
    public LayoutInflater layoutInflater() {
        return LayoutInflater.from(appCompatActivity);
    }

    @Provides
    public ExpenseAdapter provideExpenseAdapter(@ForActivity Context context, LayoutInflater inflater) {
        return new ExpenseAdapter(context, inflater);
    }

    @Provides
    public MainPresenter provideMainPresenter(final ExpenseDao expenseDao) {
        return new MainPresenterImpl(expenseDao);
    }

    public interface Exposes {

        @ActivityScope
        LayoutInflater layoutInflater();
    }
}
