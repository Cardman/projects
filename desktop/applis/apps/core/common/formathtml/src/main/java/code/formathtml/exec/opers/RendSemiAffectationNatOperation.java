package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class RendSemiAffectationNatOperation extends RendSemiAffectationOperation {

    public RendSemiAffectationNatOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ImplicitMethods _converterTo, boolean _post, StringList _names) {
        super(_content, _operatorContent, _post, _converterTo,_names);
    }

    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode left_ = getFirstNode(this);
        Argument leftStore_ = getArgument(_nodes,left_);
        Argument stored_ = getArgumentBeforeImpl(_nodes, getSettableAnc());
        Argument before_ = stored_;
        if (getConverterTo() != null) {
            String tres_ = getConverterTo().get(0).getFct().getImportedParametersTypes().get(0);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_ = ExecNumericOperation.calculateIncrDecr(leftStore_, getOperatorContent().getOper(), cast_);
            Argument conv_ = tryConvert(getConverterTo().get(0), getConverterTo().getOwnerClass(), res_, _context, _rendStack);
            if (conv_ == null) {
                return;
            }
            RendAffectationOperation.calculateChSetting(getSettable(),_nodes, conv_, _advStandards, _context, _rendStack);
            stored_ = getPrePost(isPost(),before_,conv_);
            setSimpleArgument(stored_, _nodes, _context, _rendStack);
            return;
        }
        Argument arg_ = calculateSemiChSetting(_nodes, _advStandards, _context, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

    private Argument calculateSemiChSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        RendDynOperationNode settable_ = getSettable();
        if (settable_ instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)settable_).calculateSemiSetting(_nodes, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable_).calculateSemiSetting(_nodes, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable_).calculateSemiSetting(_nodes, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable_).calculateSemiSetting(_nodes, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)settable_).calculateSemiSetting(_nodes, getOperatorContent().getOper(), isPost(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }


}
