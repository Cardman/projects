package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class RendCompoundAffectationCustOperation extends RendCompoundAffectationOperation {

    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;
    private final ExecStaticEltContent staticEltContent;

    public RendCompoundAffectationCustOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, StringList _names, boolean _staticPostEltContent) {
        super(_content, _operatorContent, _converter, _names, _staticPostEltContent);
        pair = _pair;
        formattedType = _staticEltContent.getFormattedType();
        staticEltContent = _staticEltContent;
    }

    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, formattedType, staticEltContent.getKind(), _rendStack);
        Argument res_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack).getValue();
        if (getConverter() != null) {
            Argument conv_ = tryConvert(getConverter().get(0), getConverter().getOwnerClass(), res_, _context, _rendStack);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
        }
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, getSettable());
        Argument leftArg_ = Argument.getNullableValue(argumentPair_.getArgument());
        Argument arg_ = endCalculate(_nodes,leftArg_, res_, _advStandards, _context, _rendStack,isStaticPostEltContent());
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
