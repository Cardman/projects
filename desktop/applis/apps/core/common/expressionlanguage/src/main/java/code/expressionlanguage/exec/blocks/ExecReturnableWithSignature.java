package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;

public interface ExecReturnableWithSignature {
    String id();
    String getSignature(ContextEl _ana);
}
