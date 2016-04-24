package com.babic.expensetracker.injection.component.activity;

import com.babic.expensetracker.injection.component.application.ApplicationComponent;
import com.babic.expensetracker.injection.module.ActivityModule;
import com.babic.expensetracker.injection.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ActivityModule.class
        }
)
public interface ActivityComponent extends ActivityComponentInjects, ActivityComponentExposes {
}
