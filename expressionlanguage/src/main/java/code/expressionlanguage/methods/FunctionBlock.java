package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.sml.RowCol;

public interface FunctionBlock {

    void checkFctBlocksTree(ContextEl _cont);
    void buildFctInstructions(ContextEl _cont);
    void checkFctConstrCalls(ContextEl _cont);
    boolean isStaticContext();

    RowCol getRowCol(int _offset, int _globalOffset);
    OffsetsBlock getOffset();

}
