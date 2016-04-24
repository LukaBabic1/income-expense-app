package com.babic.expensetracker.business.dao;

import com.babic.expensetracker.business.data.IncomeData;

import java.util.List;

public interface IncomeDao {

    void saveIncome(IncomeData data);

    IncomeData getIncomeById(String id);

    List<IncomeData> getAllIncomes();

    List<IncomeData> getIncomeFromInterval(long start, long end);

    void deleteAll();
}
