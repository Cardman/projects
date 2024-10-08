package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringList;

public abstract class ExecAbstractAffectOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private ExecOperationNode settable;
    private ExecOperationNode settableAnc;
    private ExecMethodOperation settableParent;
    private final ExecOperatorContent operatorContent;
    private final StringList names;
    protected ExecAbstractAffectOperation(ExecOperationContent _m, ExecOperatorContent _offset, StringList _names) {
        super(_m);
        operatorContent = _offset;
        names = _names;
    }

    public void setup() {
        settable = tryGetSettable(this);
        settableParent = tryGetSettableParent(this);
        settableAnc = anc(this);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        if (getSettableParent() instanceof ExecSafeDotOperation && getArgument(_nodes, getSettableParent().getFirstChild()) == NullStruct.NULL_VALUE) {
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_nodes, this);
            pairBefore_.setEndCalculate(true);
            pairBefore_.setIndexImplicitConv(-1);
            pairBefore_.setCalledIndexer(true);
            setQuickConvertSimpleArgument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE, _conf, names, _stack.getLastPage()), _conf, _nodes, _stack);
            return;
        }
        calculateAffect(_nodes, _conf, _stack);
    }

    protected abstract void calculateAffect(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                            ContextEl _conf, StackCall _stack);
    Struct calculateChSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _right, StackCall _stackCall) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        Struct argument_ = calculateChSetting(getSettable(), _nodes, _conf, _right, _stackCall);
        pair_.setEndCalculate(true);
        pair_.setIndexer(_conf.callsOrException(_stackCall));
        return argument_;
    }
    static Struct calculateChSetting(ExecOperationNode _set,
                                       IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _right, StackCall _stackCall){
        Struct arg_ = null;
        if (_set instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecCustArrWriteOperation) {
            arg_ = ((ExecCustArrWriteOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        if (_set instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)_set).calculateSetting(_nodes, _conf, _right, _stackCall);
        }
        return ArgumentListCall.getNull(arg_);
    }

    private static ExecOperationNode tryGetSettable(ExecAbstractAffectOperation _operation) {
        ExecOperationNode root_ = getFirstCastToBeAnalyzed(_operation);
        return dotted(root_);
    }

    static ExecOperationNode dotted(ExecOperationNode _root) {
        ExecOperationNode elt_;
        if (!(_root instanceof ExecAbstractDotOperation)) {
            elt_ = _root;
        } else {
            elt_ = ExecHelper.getLastNode((ExecMethodOperation)_root);
        }
        return elt_;
    }

    private static ExecMethodOperation tryGetSettableParent(ExecAbstractAffectOperation _operation) {
        ExecOperationNode root_ = getFirstCastToBeAnalyzed(_operation);
        ExecMethodOperation elt_;
        if (!(root_ instanceof ExecAbstractDotOperation)) {
            elt_ = ExecHelper.getParentOrNull(root_);
        } else {
            elt_ = (ExecMethodOperation)root_;
        }
        return elt_;
    }

    private static ExecOperationNode getFirstCastToBeAnalyzed(ExecAbstractAffectOperation _operation) {
        ExecOperationNode root_ = getFirstToBeAnalyzed(_operation);
        if (root_ instanceof ExecCastOperation) {
            root_ = root_.getFirstChild();
        }
        return deepSearchId(root_);
    }

    private static ExecOperationNode getFirstToBeAnalyzed(ExecAbstractAffectOperation _operation) {
        ExecOperationNode root_ = anc(_operation);
        if (root_ instanceof ExecNamedArgumentOperation) {
            root_ = root_.getFirstChild();
        }
        return deepSearchId(root_);
    }

    private static ExecOperationNode anc(ExecAbstractAffectOperation _operation) {
        ExecOperationNode root_ = _operation.getFirstChild();
        if (ExecConstLeafOperation.isFilter(root_)) {
            root_ = root_.getNextSibling();
        }
        ExecOperationNode next_ = root_;
        while (next_ != null) {
            if (!(next_ instanceof ExecNamedArgumentOperation) || ((ExecNamedArgumentOperation) next_).getIndex() == 0) {
                root_ = next_;
                next_ = null;
                continue;
            }
            next_ = next_.getNextSibling();
        }
        return root_;
    }

    static ExecOperationNode deepSearchId(ExecOperationNode _oper) {
        ExecOperationNode root_ = _oper;
        while (root_ instanceof ExecIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }

    protected static Struct firstArg(ExecAbstractAffectOperation _current, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        ArgumentsPair pairSet_ = ExecHelper.getArgumentPair(_nodes, _current.getSettable());
        return ArgumentListCall.getNull(pairSet_.getArgumentBeforeImpl());
    }

    public ExecOperationNode getSettable() {
        return settable;
    }

    public ExecMethodOperation getSettableParent() {
        return settableParent;
    }


    public ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }
    public ExecOperationNode getSettableAnc() {
        return settableAnc;
    }
}
