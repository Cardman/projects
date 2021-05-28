package code.formathtml.util;

import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;

public final class InputInfo {
    private StringList varNames = new StringList();
    private StringList varTypes = new StringList();
    private CustList<Boolean> refs = new CustList<Boolean>();

    public StringList getVarNames() {
        return varNames;
    }

    public StringList getVarTypes() {
        return varTypes;
    }

    public CustList<Boolean> getRefs() {
        return refs;
    }
}
