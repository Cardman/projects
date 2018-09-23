package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneField;

public interface InfoBlock extends WithEl, GeneField {
    int getFieldNameOffset();

    RootBlock getRooted();
    String getImportedClassName();
    void buildImportedType(ContextEl _cont);
}
