package com.babic.expensetracker.injection.component.application;

import com.babic.expensetracker.injection.module.ApplicationModule;
import com.babic.expensetracker.injection.module.ServiceModule;

public interface ApplicationComponentExposes extends ApplicationModule.Exposes,
                                                     ServiceModule.Exposes {

}
