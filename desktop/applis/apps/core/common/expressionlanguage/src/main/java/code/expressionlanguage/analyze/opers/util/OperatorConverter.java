package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;

public final class OperatorConverter {
    private final ClassMethodIdReturn fct;
    private ClassMethodIdReturn test;

    public OperatorConverter(ClassMethodIdReturn _fct) {
        fct = _fct;
    }

    public ClassMethodIdReturn getFct() {
        return fct;
    }

    public ClassMethodIdReturn getTest() {
        return test;
    }

    public void setTest(ClassMethodIdReturn _test) {
        this.test = _test;
    }

}
