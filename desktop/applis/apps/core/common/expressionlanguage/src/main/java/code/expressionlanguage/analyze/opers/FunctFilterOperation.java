package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.util.CustList;

public interface FunctFilterOperation {
    CustList<AnaResultPartType> getPartOffsets();
    InfoErrorDto getPartOffsetsErr();
}
