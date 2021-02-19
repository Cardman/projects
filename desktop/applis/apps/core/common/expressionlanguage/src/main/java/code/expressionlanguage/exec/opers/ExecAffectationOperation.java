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

public final class ExecAffectationOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private ExecOperationNode settable;
    private ExecMethodOperation settableParent;

    private final int opOffset;

    public ExecAffectationOperation(ExecOperationContent _opCont, int _opOffset) {
        super(_opCont);
        opOffset = _opOffset;
    }

    public void setup() {
        settable = tryGetSettable(this);
        settableParent = tryGetSettableParent(this);
    }
    static ExecOperationNode tryGetSettable(ExecMethodOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        ExecOperationNode elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = root_;
        } else {
            elt_ = ExecHelper.getLastNode((ExecMethodOperation)root_);
        }
        return elt_;
    }
    static ExecMethodOperation tryGetSettableParent(ExecMethodOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        ExecMethodOperation elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = ExecHelper.getParentOrNull(root_);
        } else {
            elt_ = (ExecMethodOperation)root_;
        }
        return elt_;
    }

    static ExecOperationNode getFirstToBeAnalyzed(ExecMethodOperation _operation) {
        ExecOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof ExecIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }
    public ExecOperationNode getSettable() {
        return settable;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        if (settableParent instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, getResultClass().getNames(), _stack));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
        }
        Argument rightArg_ = getLastArgument(_nodes, this);
        setRelOffsetPossibleLastPage(opOffset, _stack);
        if (settable instanceof ExecStdRefVariableOperation) {
            if (((ExecStdRefVariableOperation)settable).isDeclare()){
                CustList<ExecOperationNode> childrenNodes_ = getChildrenNodes();
                ArgumentsPair pairRight_ = ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(childrenNodes_,childrenNodes_.size()-1));
                AbstractPageEl ip_ = _stack.getLastPage();
                ip_.getRefParams().put(((ExecStdRefVariableOperation)settable).getVariableName(),ExecTemplates.getWrap(pairRight_.getWrapper()));
                setQuickNoConvertSimpleArgument(new Argument(), _conf, _nodes, _stack);
                return;
            }
        }
        Argument arg_ = calculateChSetting(settable,_nodes, _conf, rightArg_, _stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }
    static Argument calculateChSetting(ExecOperationNode _set,
                                       IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stackCall){
        Argument arg_ = null;
        if (_set instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecRefParamOperation) {
            arg_ = ((ExecRefParamOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
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
        if (_set instanceof ExecSwitchOperation) {
            arg_ = ((ExecSwitchOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecRefTernaryOperation) {
            arg_ = ((ExecRefTernaryOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }
}
