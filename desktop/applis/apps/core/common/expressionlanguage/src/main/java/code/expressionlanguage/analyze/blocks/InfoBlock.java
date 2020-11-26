package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.CustList;
import code.util.StringList;

public interface InfoBlock extends AnnotableBlock {

    boolean isStaticField();
    StringList getFieldName();
    int getFieldNameOffset();
    int getFieldNumber();
    void setFieldNumber(int _nb);
    FileBlock getFile();
    String getImportedClassName();
    String getRealImportedClassName();
    void buildImportedType(AnalyzedPageEl _page);
    void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page);
    CustList<PartOffset> getTypePartOffsets();

    boolean isFinalField();

    AccessEnum getAccess();
    CustList<AnonymousTypeBlock> getAnonymous();
    CustList<AnonymousFunctionBlock> getAnonymousFct();
}
