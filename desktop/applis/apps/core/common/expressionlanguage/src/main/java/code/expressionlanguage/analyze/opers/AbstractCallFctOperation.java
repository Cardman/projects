package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.stds.StandardMethod;

public interface AbstractCallFctOperation extends AbstractCallLeftOperation {
    ClassMethodId getClassMethodId();
    boolean isStaticMethod();
    boolean isTrueFalse();

    StandardMethod getStandardMethod();

    AnaCallFctContent getCallFctContent();
}
