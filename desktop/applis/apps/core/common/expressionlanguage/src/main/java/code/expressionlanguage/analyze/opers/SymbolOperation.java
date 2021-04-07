package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;

public interface SymbolOperation {

    int getOpOffset();

    ClassMethodIdMemberIdTypeFct getFct();

    boolean isOkNum();

}
