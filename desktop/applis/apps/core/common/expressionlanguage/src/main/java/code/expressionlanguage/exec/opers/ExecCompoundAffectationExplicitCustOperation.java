package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCompoundAffectationExplicitCustOperation extends ExecCompoundAffectationOperation {

    private final ExecStaticFctContent staticEltContent;
    private final ExecTypeFunction pair;


    public ExecCompoundAffectationExplicitCustOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ExecStaticFctContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, StringList _names, boolean _staticPostEltContent) {
        super(_opCont, _operatorContent, _converter, _names, _staticPostEltContent);
        pair = _pair;
        staticEltContent = _staticEltContent;
    }


    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecExplicitOperatorOperation.checkParametersOperatorsFormatted(this,pair,staticEltContent,_nodes,_conf,_stack);
    }

}
