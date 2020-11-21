package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;

public interface SymbolOperation {

    int getOpOffset();

    AnaTypeFct getFunction();

    String getClassName();

    boolean isOkNum();
    MemberId getMemberId();

}
