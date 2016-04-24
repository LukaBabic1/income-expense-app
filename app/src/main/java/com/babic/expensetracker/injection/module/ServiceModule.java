package com.babic.expensetracker.injection.module;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import dagger.Module;
import dagger.Provides;

@Module
public final class ServiceModule {

    private final Application application;

    public ServiceModule(final Application application) {
        this.application = application;
    }

    @Provides
    public TelephonyManager provideTelephonyManager() {
        return (TelephonyManager) application.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public interface Exposes {

        TelephonyManager telephonyManager();
    }
}
