package code.expressionlanguage.common;

import code.util.CustList;

public final class StackOperators {
    private final CustList<IndexStackOperator> values = new CustList<IndexStackOperator>();
    public boolean isEmpty() {
        return values.isEmpty();
    }
    public int size() {
        return values.size();
    }

    public int lastKey() {
        return values.last().getIndex();
    }

    public char lastValue() {
        return values.last().getOperator();
    }

    public void removeLast() {
        values.remove(values.getLastIndex());
    }

    public void addEntry(int _k, char _v) {
        values.add(new IndexStackOperator(_k, _v));
    }
}
