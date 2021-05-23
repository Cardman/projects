package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.util.IdMap;

public class ExecSemiAffectationCustOperation extends ExecSemiAffectationOperation {
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    public ExecSemiAffectationCustOperation(ExecOperationContent _opCont, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent, ExecTypeFunction _pair) {
        super(_opCont, _staticPostEltContent, _operatorContent);
        pair = _pair;
        formattedType = _staticPostEltContent.getFormattedType();
    }


    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        checkParametersOperators(_conf.getExiting(),_conf, pair, _nodes, formattedType, getStaticPostEltContent().getKind(), _stack);
    }

    @Override
    public void endCalculate(ContextEl _conf,
                             IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        end(_conf, _nodes, _right, _stack);
    }
}
