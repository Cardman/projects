package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneField;
import code.util.StringList;

public interface InfoBlock extends WithNotEmptyEl, GeneField, AnnotableBlock {
    int getFieldNameOffset();
    void buildExpressionLanguageReadOnly(ContextEl _cont);
    String getImportedClassName();
    String getRealImportedClassName();
    void buildImportedType(ContextEl _cont);
    void retrieveNames(ContextEl _cont, StringList _fieldNames);
}
