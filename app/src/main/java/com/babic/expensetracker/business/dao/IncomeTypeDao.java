package com.babic.expensetracker.business.dao;

import com.babic.expensetracker.business.data.IncomeType;

import java.util.List;

public interface IncomeTypeDao {

    void insert(IncomeType type);

    List<IncomeType> listIncomeTypes();

    void deleteAll();
}
