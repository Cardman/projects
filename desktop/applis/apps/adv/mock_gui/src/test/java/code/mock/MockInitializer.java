package code.mock;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.StackCall;

public final class MockInitializer extends DefaultInitializer {
    @Override
    public boolean exitAfterCallInt(ContextEl _owner, StackCall _stack) {
        return true;
    }

    @Override
    public boolean stop(ContextEl _owner, StackCall _stackCall) {
        return true;
    }
}
