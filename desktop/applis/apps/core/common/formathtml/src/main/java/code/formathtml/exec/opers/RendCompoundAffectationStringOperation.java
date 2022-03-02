package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class RendCompoundAffectationStringOperation extends RendCompoundAffectationOperation {


    public RendCompoundAffectationStringOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, StringList _names) {
        super(_content, _operatorContent,null, _names, false);
    }


    @Override
    protected void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument leftArg_ = getArgument(_nodes,getFirstNode(this));
        Argument rightArg_ = getArgument(_nodes,getLastNode(this));
        Argument res_ = ExecCatOperation.localSumDiff(leftArg_, rightArg_, _context);
        Argument arg_ = calculateChSetting(_nodes,res_,_advStandards,_context,_rendStack);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
