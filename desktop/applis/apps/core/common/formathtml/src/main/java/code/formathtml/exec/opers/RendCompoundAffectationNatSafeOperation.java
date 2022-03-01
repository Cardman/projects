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
        super(_content, _operatorContent, _converter, _names, false);
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
        Argument res_ = RendNumericOperation.calculateAffect(leftArg_, rightArg_, getNames(), _context,_rendStack);
        Argument arg_ = RendAffectationOperation.calculateChSetting(getSettable(),_nodes,res_,_advStandards,_context,_rendStack);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
