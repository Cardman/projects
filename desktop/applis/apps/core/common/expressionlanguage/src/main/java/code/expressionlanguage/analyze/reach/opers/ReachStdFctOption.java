package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.stds.StandardMethod;

public abstract class ReachStdFctOption extends ReachInvokingOperation {
    ReachStdFctOption(StandardMethod _standardMethod, AbstractCallFctOperation _meta, OperationNode _info) {
        super(_standardMethod, _meta, _info);
    }

}
