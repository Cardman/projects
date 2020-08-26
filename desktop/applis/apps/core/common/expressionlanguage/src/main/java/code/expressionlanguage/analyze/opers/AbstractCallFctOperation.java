package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.StandardMethod;

public interface AbstractCallFctOperation {
    String getMethodName();
    ClassMethodId getClassMethodId();
    boolean isStaticMethod();
    String getLastType();
    int getNaturalVararg();
    StandardMethod getStandardMethod();

    int getRootNumber();

    int getMemberNumber();
}
