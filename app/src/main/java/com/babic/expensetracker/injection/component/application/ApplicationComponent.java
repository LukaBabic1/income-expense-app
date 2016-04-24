package com.babic.expensetracker.injection.component.application;

import com.babic.expensetracker.injection.module.ApplicationModule;
import com.babic.expensetracker.injection.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (
        modules = {
                ApplicationModule.class,
                ServiceModule.class
        }
)
public interface ApplicationComponent extends ApplicationComponentInjects, ApplicationComponentExposes {
}
