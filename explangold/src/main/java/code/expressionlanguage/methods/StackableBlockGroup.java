package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;

public interface StackableBlockGroup extends StackableBlock {

    void exitStack(ContextEl _context);
}
