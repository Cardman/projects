package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.xml.RowCol;

public interface FunctionBlock {

    void checkFctBlocksTree(ContextEl _cont);
    void buildFctInstructions(ContextEl _cont);
    void checkFctConstrCalls(ContextEl _cont);
    boolean isStaticContext();

    RowCol getRowCol(int _i, int _tabWidth, String _attr);

}
