package code.expressionlanguage.exec.dbg;

import code.util.CustList;

public final class DefLogDbg implements AbsLogDbg {
    private final CustList<String> list = new CustList<String>();
    @Override
    public void log(String _ev) {
        list.add(_ev);
    }

    public CustList<String> getList() {
        return list;
    }
}
