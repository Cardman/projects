package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.stds.StandardMethod;

public interface AbstractCallFctOperation {
    ClassMethodId getClassMethodId();
    boolean isStaticMethod();
    boolean isTrueFalse();

    StandardMethod getStandardMethod();

    MemberId getMemberId();

    AnaTypeFct getFunction();
    AnaCallFctContent getCallFctContent();
}
