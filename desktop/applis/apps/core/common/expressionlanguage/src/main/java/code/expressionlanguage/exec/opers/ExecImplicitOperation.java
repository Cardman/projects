package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecImplicitOperation extends ExecAbstractUnaryOperation {
    private final ExecExplicitContent explicitContent;
    private final ExecTypeFunction pair;
    public ExecImplicitOperation(ExecOperationContent _opCont, ExecExplicitContent _explicitContent, ExecTypeFunction _pair) {
        super(_opCont);
        explicitContent = _explicitContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(explicitContent.getOffset(), _stack);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ =  ExecExplicitOperation.prepare(_conf.getExiting(),pair,false,arguments_, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_conf, _stack);
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }

}
