package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.Identifiable;

public interface AbsPossibleVarArg {
    boolean isVarArgToCall();
    Identifiable ident();
}
