package code.formathtml.util;

import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;

public final class InputInfo {
    private final StringList varNames = new StringList();
    private final StringList varTypes = new StringList();
    private final CustList<BoolVal> refs = new CustList<BoolVal>();

    public StringList getVarNames() {
        return varNames;
    }

    public StringList getVarTypes() {
        return varTypes;
    }

    public CustList<BoolVal> getRefs() {
        return refs;
    }
}
