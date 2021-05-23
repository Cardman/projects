package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;


public final class ExecQuickNatOperation extends ExecQuickOperation {

    private final int opOffset;

    public ExecQuickNatOperation(ExecOperationContent _opCont, int _opOffset, ImplicitMethods _converter, boolean _absorbingValue) {
        super(_opCont,_converter, _absorbingValue);
        opOffset = _opOffset;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf, StackCall _stack) {
        ExecOperationNode first_ = getFirstChild();
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _conf, _nodes, _stack);
            return;
        }
        setRelativeOffsetPossibleLastPage(opOffset, _stack);
        Argument a_ = getLastArgument(_nodes,this);
        setSimpleArgument(a_, _conf, _nodes, _stack);
    }
}
