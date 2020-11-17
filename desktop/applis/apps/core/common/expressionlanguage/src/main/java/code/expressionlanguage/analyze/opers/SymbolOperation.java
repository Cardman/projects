package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.functionid.ClassMethodId;

public interface SymbolOperation {

    int getOpOffset();

    ClassMethodId getClassMethodId();

    boolean isOkNum();
    MemberId getMemberId();

}
