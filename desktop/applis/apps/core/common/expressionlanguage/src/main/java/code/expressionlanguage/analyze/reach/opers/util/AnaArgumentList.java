package code.expressionlanguage.analyze.reach.opers.util;

import code.expressionlanguage.Argument;
import code.util.CustList;

public final class AnaArgumentList {
    private final CustList<Argument> arguments = new CustList<Argument>();

    public CustList<Argument> getArguments() {
        return arguments;
    }

}
