package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public class RendSemiAffectationCustOperation extends RendSemiAffectationOperation {
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;
    private final ExecStaticPostEltContent staticPostEltContent;

    public RendSemiAffectationCustOperation(ExecOperationContent _content, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent, ExecTypeFunction _pair, StringList _names) {
        super(_content, _operatorContent, _staticPostEltContent.isPost(),_names);
        pair = _pair;
        staticPostEltContent = _staticPostEltContent;
        formattedType = _staticPostEltContent.getFormattedType();
    }

    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode left_ = getFirstNode(this);
        Argument stored_ = getArgument(_nodes,left_);
        checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, formattedType, getStaticPostEltContent().getKind(), _rendStack);
        Argument res_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack).getValue();
        res_ = endCalculate(_nodes, stored_, res_, _advStandards, _context, _rendStack, isPost());
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }


    protected ExecStaticPostEltContent getStaticPostEltContent() {
        return staticPostEltContent;
    }

}
