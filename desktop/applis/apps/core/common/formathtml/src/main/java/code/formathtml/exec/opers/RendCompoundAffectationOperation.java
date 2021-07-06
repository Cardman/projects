package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class RendCompoundAffectationOperation extends RendAbstractAffectOperation implements CompoundedOperator {

    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converter;

    protected RendCompoundAffectationOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ImplicitMethods _converter, StringList _names) {
        super(_content, _names);
        operatorContent = _operatorContent;
        converter = _converter;
    }

    @Override
    protected void calculateAffect(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode left_ = getFirstNode(this);
        Argument leftArg_ = getArgument(_nodes,left_);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, left_);
        if (argumentPair_.isArgumentTest()){
            if (StringUtil.quickEq(operatorContent.getOper(), AbsBk.AND_LOG_EQ_SHORT) || StringUtil.quickEq(operatorContent.getOper(), AbsBk.OR_LOG_EQ_SHORT)) {
                setSimpleArgument(leftArg_, _nodes, _context, _rendStack);
                return;
            }
            Argument arg_ = RendAffectationOperation.calculateChSetting(getSettable(),_nodes, leftArg_, _advStandards, _context, _rendStack);
            setSimpleArgument(arg_, _nodes, _context, _rendStack);
            return;
        }
        calculateSpec(_nodes, _advStandards, _context, _rendStack);
    }

    protected Argument endCalculateCh(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _res, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        RendDynOperationNode settable_ = getSettable();
        if (settable_ instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)settable_).endCalculate(_nodes, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable_).endCalculate(_nodes, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable_).endCalculate(_nodes, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable_).endCalculate(_nodes, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)settable_).endCalculate(_nodes, _res, _advStandards, _context, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }
    protected abstract void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    protected ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    protected ImplicitMethods getConverter() {
        return converter;
    }

    @Override
    public String getOper() {
        return operatorContent.getOper();
    }
}
