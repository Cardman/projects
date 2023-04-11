package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.util.CustList;

public interface FunctFilterOperation {
    CustList<AnaResultPartTypeDtoInt> getPartOffsets();
    InfoErrorDto getPartOffsetsErr();
}
