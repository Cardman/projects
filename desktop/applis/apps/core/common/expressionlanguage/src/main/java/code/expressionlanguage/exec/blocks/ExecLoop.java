package code.expressionlanguage.exec.blocks;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.BreakableBlock;
import code.expressionlanguage.methods.StackableBlock;

public interface ExecLoop extends StackableBlock {

    void processLastElementLoop(ContextEl _conf);
}
