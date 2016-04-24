package com.babic.expensetracker.business.data;

public final class ExpenseData {

    public final int id;
    public final String name;
    public final String description;
    public final int amount;
    public final long timestamp;

    public ExpenseData(int id, String name, String description, int amount, long timestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseData that = (ExpenseData) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (timestamp != that.timestamp) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}