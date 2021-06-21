package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.ArrayCustWrapper;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.structs.NullStruct;
import code.util.IdMap;
import code.util.StringList;

public abstract class ExecSemiAffectationOperation extends ExecAbstractAffectOperation implements CallExecSimpleOperation {
    private final boolean post;
    private final StringList names;
    private final ExecOperatorContent operatorContent;

    protected ExecSemiAffectationOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, boolean _post, StringList _names) {
        super(_opCont);
        operatorContent = _operatorContent;
        post = _post;
        names = _names;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        if (getSettableParent() instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = getSettableParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_nodes,this);
                pairBefore_.setEndCalculate(true);
                pairBefore_.setIndexImplicitSemiFrom(-1);
                pairBefore_.setIndexImplicitSemiTo(-1);
                pairBefore_.setCalledIndexer(true);
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, names, _stack));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
        }
        calculateSpec(_nodes, _conf, _stack);
    }

    protected abstract void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                          ContextEl _conf, StackCall _stack);

    protected void end(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        ArgumentsPair pairSet_ = ExecHelper.getArgumentPair(_nodes, getSettable());
        Argument stored_ = getArgument(_nodes, getSettable());
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            Argument arg_ = endCalculate(_conf, _nodes, _right, stored_, getSettable(), post, _stack);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        if (getSettable() instanceof ExecCustArrOperation || pairSet_.getWrapper() instanceof ArrayCustWrapper) {
            Argument out_;
            if (!pair_.isCalledIndexer()) {
                pair_.setCalledIndexer(true);
                out_ = getPrePost(post, stored_, _right);
            } else {
                out_ = _right;
            }
            setSimpleArgument(out_, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(_right, _conf, _nodes, _stack);
    }

    private static Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, Argument _stored, ExecOperationNode _settable, boolean _staticPostEltContent, StackCall _stackCall) {
        Argument arg_ = null;
        if (_settable instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent, _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent, _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent, _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent, _stored, _right, _stackCall);
        }
        if (_settable instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)_settable).endCalculate(_conf, _nodes, _staticPostEltContent, _stored, _right, _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    static Argument getPrePost(boolean _post, Argument _stored,Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }

    protected ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    public boolean isPost() {
        return post;
    }

}
