package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class RendCompoundAffectationNatOperation extends RendCompoundAffectationOperation {


    public RendCompoundAffectationNatOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ImplicitMethods _converter, StringList _names) {
        super(_content, _operatorContent, _converter, _names, false);
    }


    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode left_ = getFirstNode(this);
        RendDynOperationNode right_ = getLastNode(this);
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        ImplicitMethods converter_ = getConverter();
        if (converter_ != null) {
            String tres_ = converter_.get(0).getFct().getImportedParametersTypes().get(0);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_ = RendNumericOperation.calculateAffect(leftArg_, rightArg_, getOperatorContent().getOper(), cast_, _context, _rendStack);
            Argument conv_ = tryConvert(converter_, res_, _context, _rendStack);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
            calculateChSetting(_nodes, res_, _advStandards, _context, _rendStack);
            Argument arg_ = RendSemiAffectationOperation.getPrePost(isStaticPostEltContent(),leftArg_,res_);
            setSimpleArgument(arg_, _nodes, _context, _rendStack);
            return;
        }
        Argument res_ = RendNumericOperation.calculateAffect(leftArg_, rightArg_, getOperatorContent().getOper(), getResultClass().getUnwrapObjectNb(), _context, _rendStack);
        Argument arg_ = calculateChSetting(_nodes,res_,_advStandards,_context,_rendStack);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
