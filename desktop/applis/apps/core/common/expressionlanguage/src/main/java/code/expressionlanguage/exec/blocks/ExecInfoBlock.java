package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.util.CustList;
import code.util.StringList;

public interface ExecInfoBlock extends BuildingEl {

    boolean isStaticField();
    StringList getFieldName();
    AccessEnum getAccess();
    String getImportedClassName();
    boolean isFinalField();
    String getRealImportedClassName();
    CustList<ExecAnnotContent> getAnnotationsOps();

    CustList<ExecRootBlock> getAnonymous();
    CustList<ExecAnonymousFunctionBlock> getAnonymousLambda();
    CustList<ExecAbstractSwitchMethod> getSwitchMethods();
}
