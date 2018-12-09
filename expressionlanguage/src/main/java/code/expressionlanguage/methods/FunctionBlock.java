package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;

public interface FunctionBlock {

    void buildFctInstructions(ContextEl _cont);
    boolean isStaticContext();

    OffsetsBlock getOffset();

}
