package code.expressionlanguage.exec.blocks;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public interface MethodCallingFinally {

    void removeBlockFinally(ContextEl _conf, StackCall _stack);
}
