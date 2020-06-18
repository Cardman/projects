package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.WithNotEmptyEl;

public interface ExecInfoBlock extends WithNotEmptyEl, GeneField, ExecAnnotableBlock {

    String getImportedClassName();
    String getRealImportedClassName();

    void buildImportedTypes(InfoBlock _key);
}
