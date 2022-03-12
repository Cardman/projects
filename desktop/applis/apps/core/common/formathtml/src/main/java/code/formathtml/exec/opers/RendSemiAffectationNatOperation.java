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
        Argument leftStore_ = getArgument(_nodes,getFirstNode(this));
        Argument before_ = firstArg(this,_nodes);
        ImplicitMethods converterTo_ = getConverterTo();
        if (converterTo_ != null) {
            String tres_ = converterTo_.get(0).getFct().getImportedParametersTypes().get(0);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_ = ExecNumericOperation.calculateIncrDecr(leftStore_, getOperatorContent().getOper(), cast_);
            Argument conv_ = tryConvert(converterTo_, res_, _context, _rendStack);
            if (conv_ == null) {
                return;
            }
            calculateChSetting(_nodes, conv_, _advStandards, _context, _rendStack);
            Argument arg_ =  getPrePost(isPost(),before_,conv_);
            setSimpleArgument(arg_, _nodes, _context, _rendStack);
            return;
        }
        Argument res_ = ExecNumericOperation.calculateIncrDecr(leftStore_, getOperatorContent().getOper(), getResultClass().getUnwrapObjectNb());
        calculateChSetting(_nodes,res_,_advStandards,_context,_rendStack);
        Argument arg_ = getPrePost(isPost(), before_, res_);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }


}
