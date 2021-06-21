package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecAffectationOperation extends ExecAbstractAffectOperation {

    private final StringList names;

    private final int opOffset;

    public ExecAffectationOperation(ExecOperationContent _opCont, int _opOffset,StringList _names) {
        super(_opCont);
        opOffset = _opOffset;
        names = _names;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        if (getSettableParent() instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = getSettableParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, names, _stack));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
        }
        Argument rightArg_ = getLastArgument(_nodes, this);
        setRelOffsetPossibleLastPage(opOffset, _stack);
        if (getSettable() instanceof ExecStdRefVariableOperation && ((ExecStdRefVariableOperation) getSettable()).isDeclare()) {
            CustList<ExecOperationNode> childrenNodes_ = getChildrenNodes();
            ArgumentsPair pairRight_ = ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(childrenNodes_, childrenNodes_.size() - 1));
            AbstractPageEl ip_ = _stack.getLastPage();
            ip_.getRefParams().put(((ExecStdRefVariableOperation) getSettable()).getVariableName(), ExecTemplates.getWrap(pairRight_.getWrapper()));
            setQuickNoConvertSimpleArgument(new Argument(), _conf, _nodes, _stack);
            return;
        }
        Argument arg_ = calculateChSetting(getSettable(),_nodes, _conf, rightArg_, _stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }
    static Argument calculateChSetting(ExecOperationNode _set,
                                       IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stackCall){
        Argument arg_ = null;
        if (_set instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }
}
