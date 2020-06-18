package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;
import code.util.StringList;

public interface InfoBlock extends GeneField, AnnotableBlock {
    int getFieldNameOffset();

    String getImportedClassName();
    String getRealImportedClassName();
    void buildImportedType(ContextEl _cont);
    void retrieveNames(ContextEl _cont, StringList _fieldNames);
    CustList<PartOffset> getTypePartOffsets();
}
