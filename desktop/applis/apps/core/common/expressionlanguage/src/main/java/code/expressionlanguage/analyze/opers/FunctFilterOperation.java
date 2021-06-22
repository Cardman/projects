package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.CustList;

public interface FunctFilterOperation {
    CustList<PartOffset> getPartOffsets();
}
