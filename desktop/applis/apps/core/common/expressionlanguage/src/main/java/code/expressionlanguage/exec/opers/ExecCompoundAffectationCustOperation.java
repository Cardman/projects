package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.util.IdMap;

public final class ExecCompoundAffectationCustOperation extends ExecCompoundAffectationOperation {

    private final ExecFormattedRootBlock formattedType;
    private final ExecTypeFunction pair;


    public ExecCompoundAffectationCustOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter) {
        super(_opCont, _operatorContent, _staticEltContent, _converter);
        pair = _pair;
        formattedType = _staticEltContent.getFormattedType();
    }


    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        checkParametersOperators(_conf.getExiting(),_conf, pair, _nodes, formattedType, getStaticEltContent().getKind(), _stack);
    }

}
