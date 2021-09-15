package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.StandardMethod;

public abstract class ReachStdFctOption extends ReachInvokingOperation {
    private ClassMethodId classMethodId;
    ReachStdFctOption(StandardMethod _standardMethod, AbstractCallFctOperation _meta, OperationNode _info) {
        super(_standardMethod, _meta, _info);
        classMethodId = _meta.getClassMethodId();
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }
}
