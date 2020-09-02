package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.util.CustList;
import code.util.StringList;

public final class AnaCache {
    private final CustList<AnaNamedLocalVariable> localVariables = new CustList<AnaNamedLocalVariable>();
    private final CustList<AnaNamedLoopVariable> loopVariables = new CustList<AnaNamedLoopVariable>();

    public CustList<AnaNamedLocalVariable> getLocalVariables() {
        return localVariables;
    }

    public CustList<AnaNamedLoopVariable> getLoopVariables() {
        return loopVariables;
    }

    public AnaLocalVariable getLocalVar(String _key, long _var) {
        long index_ = 0;
        for (AnaNamedLocalVariable n: localVariables) {
            if (StringList.quickEq(n.getName(),_key)) {
                if (index_ == _var) {
                    return n.getLocalVariable();
                }
                index_++;
            }
        }
        return null;
    }

    public AnaLoopVariable getLoopVar(String _key, long _var) {
        long index_ = 0;
        for (AnaNamedLoopVariable n: loopVariables) {
            if (StringList.quickEq(n.getName(),_key)) {
                if (index_ == _var) {
                    return n.getLocalVariable();
                }
                index_++;
            }
        }
        return null;
    }
}
