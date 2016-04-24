package com.babic.expensetracker.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ListView;

import com.babic.expensetracker.R;
import com.babic.expensetracker.business.data.ExpenseData;
import com.babic.expensetracker.injection.component.activity.ActivityComponent;
import com.babic.expensetracker.ui.ExpenseAdapter;
import com.babic.expensetracker.ui.presenter.MainScreenContract.MainPresenter;
import com.babic.expensetracker.ui.presenter.MainScreenContract.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.activity_main_adapter)
    protected ListView listView;

    @Bind(R.id.activity_main_name)
    protected EditText expenseName;

    @Bind(R.id.activity_main_amount)
    protected EditText expenseAmount;

    @Inject
    protected ExpenseAdapter adapter;

    @Inject
    protected MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectViews();
        listView.setAdapter(adapter);
        presenter.setView(this);
    }

    private void injectViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.listExpenses();
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void setData(final List<ExpenseData> data) {
        adapter.clear();
        adapter.addAll(data);
    }

    @Override
    public String getName() {
        return expenseName.getEditableText().toString();
    }

    @Override
    public int getAmount() {
        return Integer.parseInt(expenseAmount.getEditableText().toString());
    }

    @OnClick(R.id.fab)
    protected void addExpense() {
        presenter.addExpense();
    }
}
