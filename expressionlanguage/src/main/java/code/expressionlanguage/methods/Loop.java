package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;

public interface Loop extends BreakableBlock, StackableBlock, BuildableElMethod {

    void processLastElementLoop(ContextEl _conf);
}
