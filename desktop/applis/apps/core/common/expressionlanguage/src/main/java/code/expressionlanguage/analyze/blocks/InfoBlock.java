package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.common.AccessEnum;
import code.util.StringList;

public interface InfoBlock extends AnnotableBlock {

    ResultParsedAnnots getAnnotations();
    boolean isStaticField();
    OperationNode getRoot();
    ResultExpression getRes();

    RootBlock getDeclaringType();
    FileBlock getFile();
    String getImportedClassName();
    String getRealImportedClassName();
    void buildImportedType(AnalyzedPageEl _page);
    void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page);

    boolean isFinalField();

    AccessEnum getAccess();
    AnonymousElementsField getElements();
    void buildExpressionLanguageReadOnly(AnalyzedPageEl _page);
}
