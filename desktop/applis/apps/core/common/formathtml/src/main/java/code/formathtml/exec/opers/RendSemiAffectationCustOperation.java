package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class RendSemiAffectationCustOperation extends RendSemiAffectationOperation {
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;
    private final ExecStaticPostEltContent staticPostEltContent;

    public RendSemiAffectationCustOperation(ExecOperationContent _content, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent, ExecTypeFunction _pair, ImplicitMethods _implicitMethods, StringList _names) {
        super(_content, _operatorContent, _staticPostEltContent.isPost(),_implicitMethods,_names);
        pair = _pair;
        staticPostEltContent = _staticPostEltContent;
        formattedType = _staticPostEltContent.getFormattedType();
    }

    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument stored_ = firstArg(this,_nodes);
        checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, formattedType, getStaticPostEltContent().getKind(), _rendStack);
        Argument res_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack).getValue();
        if (getConverterTo() != null) {
            Argument conv_ = tryConvert(getConverterTo().get(0), getConverterTo().getOwnerClass(), res_, _context, _rendStack);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
        }
        calculateChSetting(_nodes, res_, _advStandards, _context, _rendStack);
        res_ = RendSemiAffectationOperation.getPrePost(isPost(),stored_,res_);
//        res_ = endCalculate(_nodes, stored_, res_, _advStandards, _context, _rendStack, isPost());
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }


    ExecStaticPostEltContent getStaticPostEltContent() {
        return staticPostEltContent;
    }

}
