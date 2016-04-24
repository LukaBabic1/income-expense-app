package com.babic.expensetracker.ui.presenter;

import com.babic.expensetracker.business.data.ExpenseData;

import java.util.List;

public final class MainScreenContract {

    private MainScreenContract() {

    }

    public interface MainPresenter {

        void setView(MainView view);

        void addExpense();

        void listExpenses();

        void deleteExpense(ExpenseData data);
    }

    public interface MainView {

        void setData(List<ExpenseData> data);

        String getName();

        int getAmount();
    }

    public final class MainViewModel {

    }
}
