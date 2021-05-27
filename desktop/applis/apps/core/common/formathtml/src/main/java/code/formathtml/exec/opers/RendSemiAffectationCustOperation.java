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

public class RendSemiAffectationCustOperation extends RendSemiAffectationOperation {
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;
    private final ExecStaticPostEltContent staticPostEltContent;

    public RendSemiAffectationCustOperation(ExecOperationContent _content, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent, ExecTypeFunction _pair) {
        super(_content, _operatorContent, _staticPostEltContent.isPost());
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
        res_ = endCalculate(_nodes, stored_, res_, _advStandards, _context, _rendStack);
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }

    private Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _stored, Argument _res, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        RendDynOperationNode settable_ = getSettable();
        if (settable_ instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)settable_).endCalculate(_nodes, isPost(), _stored, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable_).endCalculate(_nodes, isPost(), _stored, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable_).endCalculate(_nodes, isPost(), _stored, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable_).endCalculate(_nodes, isPost(), _stored, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)settable_).endCalculate(_nodes, isPost(), _stored, _res, _advStandards, _context, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    protected ExecStaticPostEltContent getStaticPostEltContent() {
        return staticPostEltContent;
    }

}
