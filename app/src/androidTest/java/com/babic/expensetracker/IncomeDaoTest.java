package com.babic.expensetracker;

import android.test.AndroidTestCase;

import com.babic.expensetracker.business.dao.AppDatabaseHelper;
import com.babic.expensetracker.business.dao.ExpenseDao;
import com.babic.expensetracker.business.dao.ExpenseDaoImpl;
import com.babic.expensetracker.business.data.IncomeData;
import com.babic.expensetracker.util.CursorUtilsImpl;

public final class IncomeDaoTest extends AndroidTestCase {

    private ExpenseDao expenseDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expenseDao = new ExpenseDaoImpl(new AppDatabaseHelper(getContext()), new CursorUtilsImpl());
        expenseDao.deleteAll();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        expenseDao.deleteAll();
    }

    public void testSavingSingleData() {
        final IncomeData data = new IncomeData();
    }
}
