package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.xml.RowCol;

public interface WithEl {

    void checkBlocksTree(ContextEl _cont);

    void buildExpressionLanguage(ContextEl _cont);

    void checkCallConstructor(ContextEl _cont);

    void processEl(ContextEl _cont);

    RowCol getRowCol(int _offset, int _tabWidth,String _attribute);
}
