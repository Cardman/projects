package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.calls.PageEl;
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

    private int opOffset;

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
            elt_ = ExecTemplates.getLastNode((ExecMethodOperation)root_);
        }
        return elt_;
    }
    static ExecMethodOperation tryGetSettableParent(ExecMethodOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        ExecMethodOperation elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = ExecTemplates.getParentOrNull(root_);
        } else {
            elt_ = (ExecMethodOperation)root_;
        }
        return elt_;
    }

    private static ExecOperationNode getFirstToBeAnalyzed(ExecMethodOperation _operation) {
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
                          ContextEl _conf) {
        if (settableParent instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        Argument rightArg_ = getLastArgument(_nodes, this);
        setRelOffsetPossibleLastPage(opOffset,_conf);
        if (settable instanceof ExecStdRefVariableOperation) {
            if (((ExecStdRefVariableOperation)settable).isDeclare()){
                CustList<ExecOperationNode> childrenNodes_ = getChildrenNodes();
                ArgumentsPair pairRight_ = ExecTemplates.getArgumentPair(_nodes, ExecTemplates.getNode(childrenNodes_,childrenNodes_.size()-1));
                PageEl ip_ = _conf.getLastPage();
                ip_.getRefParams().put(((ExecStdRefVariableOperation)settable).getVariableName(),pairRight_.getWrapper());
                setQuickNoConvertSimpleArgument(new Argument(), _conf, _nodes);
                return;
            }
        }
        Argument arg_ = calculateChSetting(settable,_nodes, _conf, rightArg_);
        setSimpleArgument(arg_, _conf, _nodes);
    }
    static Argument calculateChSetting(ExecOperationNode _set,
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right){
        Argument arg_ = null;
        if (_set instanceof ExecStdVariableOperation) {
            arg_ = ((ExecStdVariableOperation)_set).calculateSetting(_nodes, _conf, _right);
        }
        if (_set instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)_set).calculateSetting(_nodes, _conf, _right);
        }
        if (_set instanceof ExecRefParamOperation) {
            arg_ = ((ExecRefParamOperation)_set).calculateSetting(_nodes, _conf, _right);
        }
        if (_set instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)_set).calculateSetting(_nodes, _conf, _right);
        }
        if (_set instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)_set).calculateSetting(_nodes, _conf, _right);
        }
        if (_set instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)_set).calculateSetting(_nodes, _conf, _right);
        }
        return Argument.getNullableValue(arg_);
    }
}
