package com.babic.expensetracker.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.babic.expensetracker.R;
import com.babic.expensetracker.business.data.ExpenseData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public final class ExpenseAdapter extends ArrayAdapter<ExpenseData> {

    private final LayoutInflater inflater;

    public ExpenseAdapter(Context context, LayoutInflater inflater) {
        super(context, 0, new ArrayList<ExpenseData>());
        this.inflater = inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_expense_data_row_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        populate(viewHolder, getItem(position));

        return convertView;
    }

    private void populate(final ViewHolder viewHolder, final ExpenseData expenseData) {
        viewHolder.expenseName.setText(expenseData.name);
        viewHolder.expenseAmount.setText(String.valueOf(expenseData.amount));
        viewHolder.expenseTime.setText(String.valueOf(expenseData.timestamp));
    }

    protected static final class ViewHolder {

        @Bind(R.id.adapter_expense_row_item_name)
        protected TextView expenseName;

        @Bind(R.id.adapter_expense_row_item_amount)
        protected TextView expenseAmount;

        @Bind(R.id.adapter_expense_row_item_time)
        protected TextView expenseTime;

        public ViewHolder(final View rootView) {
            ButterKnife.bind(this, rootView);
        }
    }
}
