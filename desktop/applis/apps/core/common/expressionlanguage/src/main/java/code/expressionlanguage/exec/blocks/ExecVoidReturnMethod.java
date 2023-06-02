package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class ExecVoidReturnMethod extends ExecAbstractReturnMethod {

    @Override
    public void removeBlockFinally(AbstractPageEl _stack) {
        tryReturn(_stack);
    }
}
