package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;

public interface ReturnableWithSignature extends Returnable {
    String getSignature(Analyzable _ana);
}
