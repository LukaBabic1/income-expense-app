package com.babic.expensetracker.business.dao;

import com.babic.expensetracker.business.data.ExpenseType;

import java.util.List;

public interface ExpenseTypeDao {

    void insert(ExpenseType type);

    List<ExpenseType> listExpenseTypes();

    void deleteAll();
}
