package com.babic.expensetracker.ui.presenter;

import com.babic.expensetracker.business.dao.ExpenseDao;
import com.babic.expensetracker.business.data.ExpenseData;
import com.babic.expensetracker.ui.presenter.MainScreenContract.MainView;

import java.lang.ref.WeakReference;

public final class MainPresenterImpl implements MainScreenContract.MainPresenter {

    private final ExpenseDao expenseDao;

    private WeakReference<MainView> viewWeakReference;

    public MainPresenterImpl(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    @Override
    public void setView(MainView view) {
        viewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void addExpense() {
        final MainView view = getView();
        if (view != null) {
            final String name = view.getName();
            final int amount = view.getAmount();
            expenseDao.saveExpense(new ExpenseData(name, name, amount, System.currentTimeMillis()));
        }
        listExpenses();
    }

    @Override
    public void listExpenses() {
        final MainView view = getView();
        if (view != null) {
            view.setData(expenseDao.getAllExpenses());
        }
    }

    private MainView getView() {
        return viewWeakReference != null ? viewWeakReference.get() : null;
    }
}
