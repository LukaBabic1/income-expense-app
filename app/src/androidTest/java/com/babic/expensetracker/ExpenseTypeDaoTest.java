package com.babic.expensetracker;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.babic.expensetracker.business.dao.AppDatabaseHelper;
import com.babic.expensetracker.business.dao.ExpenseTypeDao;
import com.babic.expensetracker.business.dao.ExpenseTypeDaoImpl;
import com.babic.expensetracker.business.data.ExpenseType;
import com.babic.expensetracker.util.CursorUtilsImpl;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public final class ExpenseTypeDaoTest extends AndroidTestCase {

    private ExpenseTypeDao expenseTypeDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expenseTypeDao = new ExpenseTypeDaoImpl(new AppDatabaseHelper(getContext()), new CursorUtilsImpl());
        expenseTypeDao.deleteAll();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        expenseTypeDao.deleteAll();
    }

    @Test
    public void testSavingSingleValue() {
        final ExpenseType type = new ExpenseType("ručak", 0);

        expenseTypeDao.insert(type);

        final List<ExpenseType> types = expenseTypeDao.listExpenseTypes();

        Assert.assertNotNull(types);
        Assert.assertFalse(types.isEmpty());
        Assert.assertEquals(1, types.size());

        final ExpenseType fromDao = types.get(0);
        Assert.assertEquals(type.copy(fromDao.id), fromDao);
    }
}
