package code.expressionlanguage.exec.util;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;

public final class ImplicitMethods {
    private final CustList<ExecTypeFunction> converter = new CustList<ExecTypeFunction>();
    private String ownerClass="";

    public CustList<ExecTypeFunction> getConverter() {
        return converter;
    }

    public String getOwnerClass() {
        return ownerClass;
    }

    public void setOwnerClass(String _ownerClass) {
        this.ownerClass = _ownerClass;
    }

    public boolean isValidIndex(int _indexImplicit) {
        return converter.isValidIndex(_indexImplicit);
    }

    public ExecTypeFunction get(int _indexImplicit) {
        return converter.get(_indexImplicit);
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return converter.size();
    }
}
