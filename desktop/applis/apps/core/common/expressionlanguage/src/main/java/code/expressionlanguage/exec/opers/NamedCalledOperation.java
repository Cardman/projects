package code.expressionlanguage.exec.opers;

import code.expressionlanguage.opers.util.ClassMethodId;

public interface NamedCalledOperation {
    ClassMethodId getClassMethodId();
    int getDelta();
}
