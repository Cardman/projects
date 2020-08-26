package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.functionid.ClassMethodId;

public interface SymbolOperation {

    int getOpOffset();

    ClassMethodId getClassMethodId();

    boolean isOkNum();
    int getRootNumber();
    int getMemberNumber();
}
