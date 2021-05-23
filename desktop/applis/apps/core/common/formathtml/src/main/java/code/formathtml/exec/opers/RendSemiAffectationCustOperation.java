package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public class RendSemiAffectationCustOperation extends RendSemiAffectationOperation {
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    public RendSemiAffectationCustOperation(ExecOperationContent _content, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent, ExecTypeFunction _pair) {
        super(_content, _staticPostEltContent, _operatorContent);
        pair = _pair;
        formattedType = _staticPostEltContent.getFormattedType();
    }

    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode left_ = getFirstNode(this);
        Argument stored_ = getArgument(_nodes,left_);
        checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, formattedType, getStaticPostEltContent().getKind(), _stack);
        Argument res_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _stack).getValue();
        res_ = endCalculate(_nodes, _conf, stored_, res_, getSettable(), getStaticPostEltContent(), _advStandards, _context, _stack, _rendStack);
        setSimpleArgument(res_, _nodes, _context, _stack, _rendStack);
    }

    private static Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _stored, Argument _res, RendDynOperationNode _settable, ExecStaticPostEltContent _staticPostEltContent, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        if (_settable instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

}
