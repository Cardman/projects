package code.expressionlanguage.methods;

import code.expressionlanguage.PageEl;
import code.expressionlanguage.stacks.TryBlockStack;


public interface Eval extends StackableBlockGroup {
    void processToFinally(PageEl _ip, TryBlockStack _stack);
}
