package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;

public interface SymbolOperation {

    AnaOperatorContent getOperatorContent();
    ClassMethodIdMemberIdTypeFct getFct();

    boolean isOkNum();

}
