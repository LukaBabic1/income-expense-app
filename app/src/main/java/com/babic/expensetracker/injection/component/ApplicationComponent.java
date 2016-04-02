package com.babic.expensetracker.injection.component;

import com.babic.expensetracker.injection.module.ApplicationModule;
import com.babic.expensetracker.injection.module.ServiceModule;

import dagger.Component;

@Component (
        modules = {
                ApplicationModule.class,
                ServiceModule.class
        }
)
public interface ApplicationComponent extends ApplicationComponentInjects, ApplicationComponentExposes {
}
