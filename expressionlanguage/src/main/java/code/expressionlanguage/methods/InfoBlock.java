package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneField;

public interface InfoBlock extends WithNotEmptyEl, GeneField, AnnotableBlock {
    int getFieldNameOffset();

    RootBlock getRooted();
    String getImportedClassName();
    void buildImportedType(ContextEl _cont);
}
