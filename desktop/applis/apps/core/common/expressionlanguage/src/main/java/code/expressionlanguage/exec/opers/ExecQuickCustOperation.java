package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.util.IdMap;


public final class ExecQuickCustOperation extends ExecQuickOperation {

    private final ExecStaticEltContent staticEltContent;
    private final ExecTypeFunction pair;

    public ExecQuickCustOperation(ExecOperationContent _opCont, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, boolean _absorbingValue) {
        super(_opCont, _converter, _absorbingValue);
        staticEltContent = _staticEltContent;
        pair = _pair;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf, StackCall _stack) {
        ExecOperationNode first_ = getFirstChild();
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            Argument f_ = getArgument(_nodes,first_);
            setQuickConvertSimpleArgument(f_, _conf, _nodes, _stack);
            return;
        }
        checkParametersOperators(_conf.getExiting(),_conf, pair, _nodes, staticEltContent, _stack);
    }


}
