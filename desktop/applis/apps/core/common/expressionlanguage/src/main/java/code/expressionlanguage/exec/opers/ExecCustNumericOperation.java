package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.util.IdMap;

public final class ExecCustNumericOperation extends ExecNumericOperation {

    private final ExecStaticEltContent staticEltContent;
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    public ExecCustNumericOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, int _opOffset, ExecStaticEltContent _staticEltContent) {
        super(_opCont, _opOffset);
        staticEltContent = _staticEltContent;
        pair = _pair;
        formattedType = _staticEltContent.getFormattedType();
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(getOpOffset(), _stack);
        checkParametersOperators(_conf.getExiting(),_conf, pair, _nodes, formattedType, staticEltContent.getKind(), _stack);
    }

}
