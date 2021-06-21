package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendAffectationOperation extends RendAbstractAffectOperation {

    private final StringList names;

    public RendAffectationOperation(ExecOperationContent _content,StringList _names) {
        super(_content);
        names = _names;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        if (getSettableParent() instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = getSettableParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE,_context, names));
                setQuickConvertSimpleArgument(leftArg_, _nodes, _context, _rendStack);
                return;
            }
        }
        RendDynOperationNode right_ = getLastNode(this);
        if (getSettable() instanceof RendStdRefVariableOperation && ((RendStdRefVariableOperation) getSettable()).isDeclare()) {
            CustList<RendDynOperationNode> childrenNodes_ = getChildrenNodes();
            ArgumentsPair pairRight_ = getArgumentPair(_nodes, getNode(childrenNodes_, childrenNodes_.size() - 1));
            _rendStack.getLastPage().getRefParams().put(((RendStdRefVariableOperation) getSettable()).getVariableName(), pairRight_.getWrapper());
            setQuickNoConvertSimpleArgument(new Argument(), _nodes, _context, _rendStack);
            return;
        }
        Argument rightArg_ = getArgument(_nodes,right_);
        Argument arg_ = calculateChSetting(getSettable(),_nodes, rightArg_, _advStandards, _context, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }
    static Argument calculateChSetting(RendDynOperationNode _set,
                                       IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall){
        Argument arg_ = null;
        if (_set instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)_set).calculateSetting(_nodes, _right, _advStandards, _context, _rendStackCall);
        }
        if (_set instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_set).calculateSetting(_nodes, _right, _advStandards, _context, _rendStackCall);
        }
        if (_set instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_set).calculateSetting(_nodes, _right, _advStandards, _context, _rendStackCall);
        }
        if (_set instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_set).calculateSetting(_nodes, _right, _advStandards, _context, _rendStackCall);
        }
        if (_set instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)_set).calculateSetting(_nodes, _right, _advStandards, _context, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }
}
