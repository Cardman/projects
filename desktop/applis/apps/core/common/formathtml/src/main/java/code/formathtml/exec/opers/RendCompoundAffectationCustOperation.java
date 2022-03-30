package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.StringList;

public final class RendCompoundAffectationCustOperation extends RendCompoundAffectationOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticEltContent staticEltContent;

    public RendCompoundAffectationCustOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, StringList _names, boolean _staticPostEltContent) {
        super(_content, _operatorContent, _converter, _names, _staticPostEltContent);
        pair = _pair;
        staticEltContent = _staticEltContent;
    }

    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, staticEltContent, _rendStack);
        Argument res_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack).getValue();
        process(this, _nodes, _context, _rendStack, res_);
    }

}
