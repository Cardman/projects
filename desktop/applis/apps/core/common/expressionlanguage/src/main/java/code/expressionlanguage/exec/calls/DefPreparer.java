package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class DefPreparer extends DefPreparerNoAbs {
    @Override
    public boolean initType(ContextEl _context, StackCall _stack) {
        return false;
    }

    @Override
    public boolean isPolymorph(ContextEl _context, StackCall _stack) {
        return false;
    }
}
