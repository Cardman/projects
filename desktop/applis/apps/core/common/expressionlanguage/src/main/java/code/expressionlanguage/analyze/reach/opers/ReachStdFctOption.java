package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.functionid.ClassMethodId;

public abstract class ReachStdFctOption extends ReachInvokingOperation {
    private ClassMethodId classMethodId;
    ReachStdFctOption(AbstractCallFctOperation _meta, OperationNode _info) {
        super(_meta, _info);
        classMethodId = _meta.getClassMethodId();
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }
}
