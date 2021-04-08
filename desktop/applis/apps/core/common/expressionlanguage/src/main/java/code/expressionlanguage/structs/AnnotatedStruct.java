package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public interface AnnotatedStruct extends DisplayableStruct {
    String getFileName();
    ExecRootBlock getOwner();
    String getDeclaringClass();
    CustList<CustList<ExecOperationNode>> getAnnotationsOps();
    CustList<ExecAnonymousFunctionBlock> getAnonymousLambda();
    CustList<ExecAbstractSwitchMethod> getSwitchMethods();
}
