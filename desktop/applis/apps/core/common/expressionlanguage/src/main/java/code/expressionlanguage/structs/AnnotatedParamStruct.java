package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public interface AnnotatedParamStruct extends AnnotatedStruct {
    boolean isVararg();
    Identifiable fid();
    Identifiable realId();
    ExecTypeFunction getPair();
    ExecRootBlock getPairType();
    ExecMemberCallingsBlock getCallee();
}
