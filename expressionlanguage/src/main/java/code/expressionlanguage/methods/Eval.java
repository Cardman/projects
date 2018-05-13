package code.expressionlanguage.methods;

import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.stacks.TryBlockStack;


public interface Eval extends StackableBlockGroup {
    void processToFinally(AbstractPageEl _ip, TryBlockStack _stack);
}
