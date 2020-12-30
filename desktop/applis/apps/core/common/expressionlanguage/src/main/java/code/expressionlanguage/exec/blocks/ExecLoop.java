package code.expressionlanguage.exec.blocks;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.stacks.LoopBlockStack;

public interface ExecLoop extends StackableBlock {

    void processLastElementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stack);
}
