package com.babic.expensetracker.injection;

import com.babic.expensetracker.application.ExpenseTrackerApplication;
import com.babic.expensetracker.injection.component.ApplicationComponent;
import com.babic.expensetracker.injection.component.DaggerApplicationComponent;
import com.babic.expensetracker.injection.module.ServiceModule;

public final class ComponentFactory {

    private ComponentFactory() {
        throw new AssertionError();
    }

    public static ApplicationComponent createApplicationComponent(final ExpenseTrackerApplication application) {
        return DaggerApplicationComponent.builder().serviceModule(new ServiceModule(application)).build();
    }
}
