package code.expressionlanguage.opers;

import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.util.ClassMethodId;

public interface SymbolOperation extends Operable {

    int getOpOffset();

    ClassMethodId getClassMethodId();

    boolean isOkNum();
}
