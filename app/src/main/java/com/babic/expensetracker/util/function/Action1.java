package com.babic.expensetracker.util.function;

/**
 * A one-argument action.
 */
public interface Action1<T> extends Action {
    void call(T t);
}
