package com.babic.expensetracker.injection.module;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.babic.expensetracker.injection.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @ActivityScope
    public LayoutInflater layoutInflater() {
        return LayoutInflater.from(appCompatActivity);
    }

    public interface Exposes {

        @ActivityScope
        LayoutInflater layoutInflater();
    }
}
