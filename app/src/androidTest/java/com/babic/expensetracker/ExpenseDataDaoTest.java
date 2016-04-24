package com.babic.expensetracker;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.babic.expensetracker.business.dao.AppDatabaseHelper;
import com.babic.expensetracker.business.dao.ExpenseDao;
import com.babic.expensetracker.business.dao.ExpenseDaoImpl;
import com.babic.expensetracker.business.data.ExpenseData;
import com.babic.expensetracker.util.CursorUtilsImpl;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public final class ExpenseDataDaoTest extends AndroidTestCase {

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
        final ExpenseData data = new ExpenseData("foo", "bar", 10, 10L);

        expenseDao.saveExpense(data);

        final List<ExpenseData> allData = expenseDao.getAllExpenses();

        Assert.assertNotNull(allData);
        Assert.assertEquals(1, allData.size());

        final ExpenseData fromDao = allData.get(0);

        Assert.assertEquals(data.copy(fromDao.id), fromDao);
    }

    @Test
    public void testGetItemFromDaoById()     {
        final ExpenseData data1 = new ExpenseData(50, "foo", "bar", 10, 10L);
        final ExpenseData data2 = new ExpenseData(100, "luka", "luka", 20, 20L);

        expenseDao.saveExpense(data1);
        expenseDao.saveExpense(data2);

        final ExpenseData fromDao = expenseDao.getExpenseById(data2.id);

        Assert.assertNotNull(fromDao);
        Assert.assertEquals(data2, fromDao);
    }

    @Test
    public void testUpdateItem() {
        final ExpenseData data1 = new ExpenseData(50, "foo", "bar", 10, 10L);
        final ExpenseData data2 = new ExpenseData(100, "luka", "luka", 20, 20L);

        expenseDao.saveExpense(data1);
        expenseDao.saveExpense(data2);

        final ExpenseData result = new ExpenseData(50, "fdsfs", "fdsf", 156, 789L);
        expenseDao.updateExpense(result);

        final ExpenseData fromDao = expenseDao.getExpenseById(result.id);

        Assert.assertNotNull(fromDao);
        Assert.assertEquals(result, fromDao);
    }
}
