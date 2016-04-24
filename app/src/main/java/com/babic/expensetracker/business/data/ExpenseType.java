package com.babic.expensetracker.business.data;

public final class ExpenseType {

    public final int id;
    public final String name;
    public final int usageCount;

    public ExpenseType(int id, String name, int usageCount) {
        this.id = id;
        this.name = name;
        this.usageCount = usageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseType that = (ExpenseType) o;

        if (id != that.id) return false;
        if (usageCount != that.usageCount) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + usageCount;
        return result;
    }
}
