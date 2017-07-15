package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.util.StringList;
import code.xml.RowCol;

public interface FunctionBlock {

    void checkBlocksTree(ContextEl _cont);
    void buildInstructions(ContextEl _cont);
    void checkConstrCalls(ContextEl _cont);
    boolean isStaticContext();
    StringList getParametersNames();
    StringList getParametersTypes();

    RowCol getRowCol(int _i, int _tabWidth, String _attr);

}
