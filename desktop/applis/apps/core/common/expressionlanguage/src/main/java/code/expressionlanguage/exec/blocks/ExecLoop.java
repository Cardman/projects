package code.expressionlanguage.exec.blocks;
import code.expressionlanguage.ContextEl;

public interface ExecLoop extends StackableBlock {

    void processLastElementLoop(ContextEl _conf);
}
