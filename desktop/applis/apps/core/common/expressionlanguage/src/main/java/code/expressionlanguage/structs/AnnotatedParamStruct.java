package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;

public interface AnnotatedParamStruct extends AnnotatedStruct {
    ExecTypeFunction getPair();
    ExecRootBlock getPairType();
    ExecMemberCallingsBlock getCallee();
    CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams();
}
