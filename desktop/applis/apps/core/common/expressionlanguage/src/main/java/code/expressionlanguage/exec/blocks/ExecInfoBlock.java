package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.util.CustList;
import code.util.StringList;

public interface ExecInfoBlock extends WithNotEmptyEl, ExecAnnotableBlock {

    boolean isStaticField();
    StringList getFieldName();
    AccessEnum getAccess();
    String getImportedClassName();
    boolean isFinalField();
    String getRealImportedClassName();

    CustList<ExecRootBlock> getAnonymous();
    CustList<ExecAnonymousFunctionBlock> getAnonymousLambda();
}
