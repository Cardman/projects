package code.expressionlanguage.opers.exec;

import code.expressionlanguage.opers.util.ClassMethodId;

public interface NamedCalledOperation {
    ClassMethodId getClassMethodId();
    int getDelta();
}
