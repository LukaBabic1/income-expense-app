package com.babic.expensetracker.business.dao;

import com.babic.expensetracker.business.data.ExpenseData;

import java.util.List;

public interface ExpenseDao {

    void save(ExpenseData data);

    void update(ExpenseData data);

    void delete(int id);

    ExpenseData getById(int id);

    List<ExpenseData> getAll();

    List<ExpenseData> getExpenseFromInterval(long start, long end);

    void deleteAll();
}
