package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class RendCompoundAffectationNatSafeOperation extends RendCompoundAffectationOperation {

    public RendCompoundAffectationNatSafeOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ImplicitMethods _converter, StringList _names) {
        super(_content, _operatorContent, _converter, _names);
    }


    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode left_ = getFirstNode(this);
        RendDynOperationNode right_ = getLastNode(this);
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        if (StringUtil.quickEq(getOperatorContent().getOper(), AbsBk.NULL_EQ_SHORT) && !leftArg_.isNull()) {
            setSimpleArgument(leftArg_, _nodes, _context, _rendStack);
            return;
        }
        Argument arg_ = calculateCompoundChSetting(_nodes, rightArg_, _advStandards, _context, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

    private Argument calculateCompoundChSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _rightArg, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        RendDynOperationNode settable_ = getSettable();
        StringList names_ = getNames();
        if (settable_ instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)settable_).calculateCompoundSetting(_nodes, _rightArg, names_, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable_).calculateCompoundSetting(_nodes, _rightArg, names_, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable_).calculateCompoundSetting(_nodes, _rightArg, names_, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable_).calculateCompoundSetting(_nodes, _rightArg, names_, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)settable_).calculateCompoundSetting(_nodes, _rightArg, names_, _advStandards, _context, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

}
