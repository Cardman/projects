package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;

public interface CallingFinally extends StackableBlock {

    void removeBlockFinally(ContextEl _conf);
}
