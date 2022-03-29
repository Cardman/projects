package code.expressionlanguage.exec.blocks;

import code.util.CustList;

public interface ExecAccessedFct {
    CustList<ExecRootBlock> getReserved();

    CustList<ExecRootBlock> getAnonymous();
    CustList<ExecAnonymousFunctionBlock> getAnonymousLambda();

    CustList<ExecAbstractSwitchMethod> getSwitchMethods();
}
