package code.expressionlanguage.opers;

import code.expressionlanguage.opers.util.ClassMethodId;

public interface SymbolOperation {

    int getOpOffset();

    ClassMethodId getClassMethodId();

    boolean isOkNum();
}
