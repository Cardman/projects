package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecVoidReturnMethod extends ExecAbstractReturnMethod {

    @Override
    public void removeBlockFinally(ContextEl _conf, StackCall _stack) {
        tryReturn(_stack);
    }
}
