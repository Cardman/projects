package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.util.IdMap;
import code.util.StringList;

public final class ExecSemiAffectationCustOperation extends ExecSemiAffectationOperation {
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;
    private final ExecStaticPostEltContent staticPostEltContent;

    public ExecSemiAffectationCustOperation(ExecOperationContent _opCont, ExecStaticPostEltContent _staticPostEltContent, ImplicitMethods _implicitMethods, ExecOperatorContent _operatorContent, ExecTypeFunction _pair, StringList _names) {
        super(_opCont, _operatorContent, _staticPostEltContent.isPost(),_implicitMethods,_names);
        pair = _pair;
        staticPostEltContent = _staticPostEltContent;
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

    ExecStaticPostEltContent getStaticPostEltContent() {
        return staticPostEltContent;
    }

}
