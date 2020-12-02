package code.formathtml.util;

import code.util.BooleanList;
import code.util.StringList;

public final class InputInfo {
    private StringList varNames = new StringList();
    private StringList varTypes = new StringList();
    private BooleanList refs = new BooleanList();

    public StringList getVarNames() {
        return varNames;
    }

    public StringList getVarTypes() {
        return varTypes;
    }

    public BooleanList getRefs() {
        return refs;
    }
}
