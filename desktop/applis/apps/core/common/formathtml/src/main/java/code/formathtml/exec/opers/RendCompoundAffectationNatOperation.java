package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class RendCompoundAffectationNatOperation extends RendCompoundAffectationOperation {


    public RendCompoundAffectationNatOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ImplicitMethods _converter) {
        super(_content, _operatorContent, _staticEltContent, _converter);
    }


    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode left_ = getFirstNode(this);
        RendDynOperationNode right_ = getLastNode(this);
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        if (StringUtil.quickEq(getOperatorContent().getOper(), "???=") && !leftArg_.isNull()) {
            setSimpleArgument(leftArg_, _nodes, _context, _stack, _rendStack);
            return;
        }
        if (getConverter() != null) {
            String tres_ = getConverter().get(0).getFct().getImportedParametersTypes().get(0);
            StringList argType_ = new StringList(tres_);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_ = RendNumericOperation.calculateAffect(leftArg_, rightArg_, getOperatorContent().getOper(), false, argType_, cast_, _context, _stack);
            Argument conv_ = tryConvert(getConverter().get(0),getConverter().getOwnerClass(), res_, _context, _stack);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
            Argument arg_ = endCalculateCh(_nodes, _conf, res_, getSettable(), _advStandards, _context, _stack, _rendStack);
            setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
            return;
        }
        Argument arg_ = calculateCompoundChSetting(_nodes, _conf, rightArg_, _advStandards, _context, _stack, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }

    private Argument calculateCompoundChSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _rightArg, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        RendDynOperationNode settable_ = getSettable();
        if (settable_ instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable_ instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable_ instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable_ instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable_ instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)settable_).calculateCompoundSetting(_nodes, _conf, getOperatorContent().getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

}
