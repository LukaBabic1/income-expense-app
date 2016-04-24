package com.babic.expensetracker.injection.module;

import com.babic.expensetracker.business.aggregator.ExpenseAggregator;
import com.babic.expensetracker.business.aggregator.IncomeAggregator;
import com.babic.expensetracker.business.dao.ExpenseDao;

import javax.inject.Singleton;

import dagger.Module;

@Module
public final class DataModule {

    @Singleton
    ExpenseDao provideAppDao() {
        return null;
    }

    @Singleton
    ExpenseAggregator provideExpenseAggregator() {
        return null;
    }

    @Singleton
    IncomeAggregator provideIncomeAggregator() {
        return null;
    }
}
