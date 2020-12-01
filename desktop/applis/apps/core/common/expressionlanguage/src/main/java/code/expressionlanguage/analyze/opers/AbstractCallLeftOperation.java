package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.fwd.opers.AnaArrContent;

public interface AbstractCallLeftOperation {
    boolean isErrLeftValue();
    AnaArrContent getArrContent();
}
