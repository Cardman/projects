package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.CustList;

public interface AnnotatedStruct extends DisplayableStruct {
    String getFileName();
    ExecRootBlock getOwner();
    ExecFormattedRootBlock getFormatted();

    CustList<CustList<ExecOperationNode>> getAnnotationsOps();
    CustList<ExecAnonymousFunctionBlock> getAnonymousLambda();
    CustList<ExecAbstractSwitchMethod> getSwitchMethods();
}
