package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.common.AccessEnum;
import code.util.CustList;
import code.util.StringList;

public interface InfoBlock extends AnnotableBlock {

    ResultParsedAnnots getAnnotations();
    boolean isStaticField();
    StringList getFieldName();
    int getFieldNumber();
    void setFieldNumber(int _nb);
    RootBlock getDeclaringType();
    FileBlock getFile();
    String getImportedClassName();
    String getRealImportedClassName();
    void buildImportedType(AnalyzedPageEl _page);
    void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page);

    boolean isFinalField();

    AccessEnum getAccess();
    CustList<AnonymousTypeBlock> getAnonymous();
    CustList<NamedCalledFunctionBlock> getAnonymousFct();
    CustList<SwitchMethodBlock> getSwitchMethods();
}
