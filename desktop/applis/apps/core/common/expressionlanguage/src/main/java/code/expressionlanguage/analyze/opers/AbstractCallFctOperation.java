package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.functionid.ClassMethodId;

public interface AbstractCallFctOperation {
    String getMethodName();
    ClassMethodId getClassMethodId();
    boolean isStaticMethod();
    String getLastType();
    int getNaturalVararg();
}
