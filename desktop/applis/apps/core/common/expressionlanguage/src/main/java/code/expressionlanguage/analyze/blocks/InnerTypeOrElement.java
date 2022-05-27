package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.util.StringList;

public interface InnerTypeOrElement extends InfoBlock {
    String getUniqueFieldName();
    OperationNode getRoot();
    void buildExpressionLanguageReadOnly(AnalyzedPageEl _page);
    int getFieldNameOffset();

    AnaElementContent getElementContent();

    StringList getNameErrors();

    void addNameErrors(String _err);
}
