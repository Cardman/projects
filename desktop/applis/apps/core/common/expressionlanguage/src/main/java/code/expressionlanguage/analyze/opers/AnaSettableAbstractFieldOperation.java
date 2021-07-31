package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;

public interface AnaSettableAbstractFieldOperation {
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page,SettableAbstractFieldOperation _settable);
    String getFieldName();
    boolean isBaseAccess();
    boolean isSuperAccess();
    AnaResultPartType getPartOffsets();
    int getDelta();
}
