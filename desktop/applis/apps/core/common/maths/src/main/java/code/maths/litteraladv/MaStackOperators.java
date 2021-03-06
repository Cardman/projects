package code.maths.litteraladv;

import code.util.CustList;

public final class MaStackOperators {
    private final CustList<MaIndexStackOperator> opers = new CustList<MaIndexStackOperator>();
    public boolean empty() {
        return opers.isEmpty();
    }
    public int nb() {
        return opers.size();
    }

    public int ind() {
        return opers.last().getInd();
    }

    public char oper() {
        return opers.last().getOper();
    }

    public void remove() {
        opers.remove(opers.getLastIndex());
    }

    public void add(int _k, char _v) {
        opers.add(new MaIndexStackOperator(_k, _v));
    }

}
