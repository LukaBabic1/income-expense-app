package com.babic.expensetracker;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.babic.expensetracker.business.dao.AppDatabaseHelper;
import com.babic.expensetracker.business.dao.IncomeDao;
import com.babic.expensetracker.business.dao.IncomeDaoImpl;
import com.babic.expensetracker.business.data.IncomeData;
import com.babic.expensetracker.util.CursorUtilsImpl;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public final class IncomeDataDaoTest extends AndroidTestCase {

    private IncomeDao incomeDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        incomeDao = new IncomeDaoImpl(new AppDatabaseHelper(getContext()), new CursorUtilsImpl());
        incomeDao.deleteAll();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        incomeDao.deleteAll();
    }

    @Test
    public void testSavingSingleData() {
        final IncomeData data = new IncomeData("plaća", "plaća", 8000, 1000L);

        incomeDao.saveIncome(data);

        final List<IncomeData> datas = incomeDao.getAllIncomes();
        Assert.assertNotNull(datas);
        Assert.assertFalse(datas.isEmpty());
        Assert.assertEquals(1, datas.size());

        final IncomeData fromDao = datas.get(0);

        Assert.assertEquals(data.copy(fromDao.id), fromDao);
    }
}
