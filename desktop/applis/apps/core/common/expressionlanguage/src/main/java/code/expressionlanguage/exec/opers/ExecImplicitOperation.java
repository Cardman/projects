package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecImplicitOperation extends ExecAbstractUnaryOperation {
    private ExecExplicitContent explicitContent;
    private ExecTypeFunction pair;
    public ExecImplicitOperation(ExecOperationContent _opCont, ExecExplicitContent _explicitContent, ExecTypeFunction _pair) {
        super(_opCont);
        explicitContent = _explicitContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelOffsetPossibleLastPage(explicitContent.getOffset(), _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ =  ExecExplicitOperation.prepare(_conf.getExiting(),pair,false,arguments_, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

}
