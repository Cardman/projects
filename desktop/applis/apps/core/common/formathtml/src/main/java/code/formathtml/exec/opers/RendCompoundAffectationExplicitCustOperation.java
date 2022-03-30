package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.StringList;

public final class RendCompoundAffectationExplicitCustOperation extends RendCompoundAffectationOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticFctContent staticEltContent;

    public RendCompoundAffectationExplicitCustOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ExecStaticFctContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, StringList _names, boolean _staticPostEltContent) {
        super(_content, _operatorContent, _converter, _names, _staticPostEltContent);
        pair = _pair;
        staticEltContent = _staticEltContent;
    }

    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        ArgumentWrapper argumentWrapper_ = RendExplicitOperatorOperation.checkParametersOperatorsFormatted(this, pair, staticEltContent, _nodes, _context, _rendStack);
        setWrapper(_nodes, argumentWrapper_);
        process(this,_nodes, _context, _rendStack,argumentWrapper_.getValue());
    }

}
