package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustNumericOperation extends ExecNumericOperation {

    private ExecStaticEltContent staticEltContent;
    private final ExecTypeFunction pair;

    public ExecCustNumericOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _opCont, int _opOffset, ExecStaticEltContent _staticEltContent) {
        super(_opCont, _opOffset);
        staticEltContent = _staticEltContent;
        pair = new ExecTypeFunction(_rootBlock,_named);
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        setRelOffsetPossibleLastPage(getOpOffset(), _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        ExecInvokingOperation.checkParametersOperators(_conf.getExiting(),_conf, pair, arguments_, staticEltContent.getClassName(), staticEltContent.getKind());
    }

}
