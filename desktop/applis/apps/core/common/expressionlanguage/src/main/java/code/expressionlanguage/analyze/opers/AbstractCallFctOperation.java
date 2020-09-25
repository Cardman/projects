package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.stds.StandardMethod;

public interface AbstractCallFctOperation {
    ClassMethodId getClassMethodId();
    boolean isStaticMethod();
    boolean isTrueFalse();

    StandardMethod getStandardMethod();

    int getRootNumber();

    int getMemberNumber();
    AnaCallFctContent getCallFctContent();
}
