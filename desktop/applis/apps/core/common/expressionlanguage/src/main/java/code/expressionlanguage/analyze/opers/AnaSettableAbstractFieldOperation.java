package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;

public interface AnaSettableAbstractFieldOperation {
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page,SettableAbstractFieldOperation _settable);
    String getFieldName();
    boolean isBaseAccess();
    boolean isSuperAccess();
    CustList<PartOffset> getPartOffsets();
    int getDelta();
}
