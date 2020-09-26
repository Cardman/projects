package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneField;
import code.util.CustList;

public interface ExecInfoBlock extends WithNotEmptyEl, GeneField, ExecAnnotableBlock {

    AccessEnum getAccess();
    String getImportedClassName();
    boolean isFinalField();
    String getRealImportedClassName();

    CustList<ExecRootBlock> getAnonymous();
    CustList<ExecAnonymousFunctionBlock> getAnonymousLambda();
}
