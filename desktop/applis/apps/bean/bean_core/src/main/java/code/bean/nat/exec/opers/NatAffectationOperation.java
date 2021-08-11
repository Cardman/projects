package code.bean.nat.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class NatAffectationOperation extends NatAbstractAffectOperation {

    public NatAffectationOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    protected void calculateAffect(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode right_ = getLastNode(this);
        Argument rightArg_ = getArgument(_nodes,right_);
        Argument arg_ = calculateChSetting(getSettable(),_nodes, rightArg_, _advStandards, _context, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

    static Argument calculateChSetting(RendDynOperationNode _set,
                                       IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall){
        Argument arg_ = ((NatSettableFieldOperation) _set).calculateSetting(_nodes, _right, _advStandards, _context, _rendStackCall);
        return Argument.getNullableValue(arg_);
    }
}
