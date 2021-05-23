package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendCompoundAffectationCustOperation extends RendCompoundAffectationOperation {

    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    public RendCompoundAffectationCustOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter) {
        super(_content, _operatorContent, _staticEltContent, _converter);
        pair = _pair;
        formattedType = _staticEltContent.getFormattedType();
    }

    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, formattedType, getStaticEltContent().getKind(), _stack);
        Argument res_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _stack).getValue();
        if (getConverter() != null) {
            Argument conv_ = tryConvert(getConverter().get(0), getConverter().getOwnerClass(), res_, _context, _stack);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
        }
        Argument arg_ = endCalculateCh(_nodes, _conf, res_, getSettable(), _advStandards, _context, _stack, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }

}
