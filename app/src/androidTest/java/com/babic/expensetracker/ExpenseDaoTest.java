package com.babic.expensetracker;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.babic.expensetracker.business.dao.ExpenseDao;
import com.babic.expensetracker.business.dao.ExpenseDaoImpl;
import com.babic.expensetracker.business.dao.AppDatabaseHelper;
import com.babic.expensetracker.business.data.ExpenseData;
import com.babic.expensetracker.util.CursorUtilsImpl;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public final class ExpenseDaoTest extends AndroidTestCase {

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

    @Test
    public void testSavingSingleData() {
        final ExpenseData data = new ExpenseData(0, "foo", "bar", 10, 10L);

        expenseDao.saveExpense(data);

        final List<ExpenseData> allData = expenseDao.getAllExpenses();

        Assert.assertNotNull(allData);
        Assert.assertEquals(1, allData.size());
        Assert.assertEquals(data, allData.get(0));
    }
}
