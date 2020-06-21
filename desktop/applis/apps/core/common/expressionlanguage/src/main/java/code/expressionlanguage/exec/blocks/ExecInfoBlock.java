package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.analyze.blocks.InfoBlock;

public interface ExecInfoBlock extends WithNotEmptyEl, GeneField, ExecAnnotableBlock {

    AccessEnum getAccess();
    String getImportedClassName();
    boolean isFinalField();
    String getRealImportedClassName();

    void buildImportedTypes(InfoBlock _key);
}
