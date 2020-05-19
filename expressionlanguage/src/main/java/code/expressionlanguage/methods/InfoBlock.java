package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneField;

public interface InfoBlock extends WithNotEmptyEl, GeneField, AnnotableBlock {
    int getFieldNameOffset();
    void buildExpressionLanguageReadOnly(ContextEl _cont);
    void buildExpressionLanguage(ContextEl _cont);
    String getImportedClassName();
    String getRealImportedClassName();
    void buildImportedType(ContextEl _cont);
    void setAssignmentBefore(ContextEl _an);
    void setAssignmentAfter(ContextEl _an);
}
