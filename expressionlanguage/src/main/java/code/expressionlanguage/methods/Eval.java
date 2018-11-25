package code.expressionlanguage.methods;

import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.stacks.TryBlockStack;


public interface Eval extends StackableBlockGroup, BreakableBlock {
    void processToFinally(AbstractPageEl _ip, TryBlockStack _stack);
}
