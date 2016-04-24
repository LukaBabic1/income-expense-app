package com.babic.expensetracker.business.dao;

import com.babic.expensetracker.business.data.ExpenseData;

import java.util.List;

public interface ExpenseDao {

    void saveExpense(ExpenseData data);

    void updateExpense(ExpenseData data);

    ExpenseData getExpenseById(int id);

    List<ExpenseData> getAllExpenses();

    List<ExpenseData> getExpenseFromInterval(long start, long end);

    void deleteAll();
}
