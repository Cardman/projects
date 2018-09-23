package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;

public interface Loop extends BreakableBlock, StackableBlockGroup {

    void processLastElementLoop(ContextEl _conf);
}
