package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;

public interface ReturnableWithSignature extends Returnable {
    String getSignature(ContextEl _ana);
}
