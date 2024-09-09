package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.RangeChecker;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecRangeOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private final int opOffset;
    private final boolean implicitMiddle;
    public ExecRangeOperation(ExecOperationContent _m, int _opOffset, boolean _implicitMiddle) {
        super(_m);
        opOffset = _opOffset;
        implicitMiddle = _implicitMiddle;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(opOffset, _stack);
        CustList<Struct> args_ = getArguments(_nodes, this);
        Struct r_ = range(_conf, _stack, args_, implicitMiddle);
        setSimpleArgument(r_, _conf, _nodes, _stack);
    }

    public static Struct range(ContextEl _conf, StackCall _stack, CustList<Struct> _args, boolean _implicitMiddle) {
        Struct a_ = ExecHelper.getArgument(_args,0);
        Struct r_;
        if (_args.size() == 3) {
            Struct b_ = ExecHelper.getArgument(_args,1);
            Struct c_ = ExecHelper.getArgument(_args,2);
            r_ = RangeChecker.range(_conf, _stack,a_,b_,c_);
        } else if (_args.size() == 2) {
            Struct c_ = ExecHelper.getArgument(_args,1);
            if (_implicitMiddle) {
                r_ = RangeChecker.rangeUnlimitStep(_conf, _stack,a_,c_);
            } else {
                r_ = RangeChecker.range(_conf, _stack,a_,c_);
            }
        } else {
            r_ = RangeChecker.range(_conf, _stack,a_);
        }
        return r_;
    }
}
