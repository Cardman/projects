package code.expressionlanguage.analyze.reach.opers.util;

import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class AnaArgumentList {
    private final CustList<Struct> arguments = new CustList<Struct>();

    public CustList<Struct> getArguments() {
        return arguments;
    }

}
