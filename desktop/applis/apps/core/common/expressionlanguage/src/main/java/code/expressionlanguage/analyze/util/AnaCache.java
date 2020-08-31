package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.util.CustList;

public final class AnaCache {
    private final CustList<AnaNamedLocalVariable> localVariables = new CustList<AnaNamedLocalVariable>();
    private final CustList<AnaNamedLoopVariable> loopVariables = new CustList<AnaNamedLoopVariable>();

    public CustList<AnaNamedLocalVariable> getLocalVariables() {
        return localVariables;
    }

    public CustList<AnaNamedLoopVariable> getLoopVariables() {
        return loopVariables;
    }
}
