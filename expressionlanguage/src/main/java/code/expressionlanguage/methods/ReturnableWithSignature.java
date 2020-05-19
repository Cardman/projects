package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;

public interface ReturnableWithSignature extends Returnable {
    String getSignature(ContextEl _ana);
}
