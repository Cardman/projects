package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.ArrayCustWrapper;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;
import code.util.StringList;

public abstract class ExecSemiAffectationOperation extends ExecAbstractAffectOperation implements CallExecSimpleOperation {
    private final boolean post;
    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converterTo;

    protected ExecSemiAffectationOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, boolean _post, ImplicitMethods _converterTo, StringList _names) {
        super(_opCont, _operatorContent.getOpOffset(), _names);
        operatorContent = _operatorContent;
        converterTo = _converterTo;
        post = _post;
    }

    @Override
    protected void calculateAffect(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        calculateSpec(_nodes, _conf, _stack);
    }

    protected abstract void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                          ContextEl _conf, StackCall _stack);

    protected void end(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        ArgumentsPair pairSet_ = ExecHelper.getArgumentPair(_nodes, getSettableAnc());
        Argument stored_ = Argument.getNullableValue(pairSet_.getArgumentBeforeImpl());
        int indexImplicit_ = pair_.getIndexImplicitSemiTo();
        if (ImplicitMethods.isValidIndex(converterTo,indexImplicit_)) {
            pair_.setIndexImplicitSemiTo(ExecOperationNode.processConverter(_conf,_right, converterTo,indexImplicit_, _stack));
            return;
        }
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            Argument arg_ = endCalculate(_conf, _nodes, _right, stored_, getSettable(), post, _stack);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        if (isIndexer(getSettable(),_nodes)) {
            Argument out_ = callIndexer(_right, pair_, stored_, post);
            setSimpleArgument(out_, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(_right, _conf, _nodes, _stack);
    }
    static boolean isIndexer(ExecOperationNode _settable, IdMap<ExecOperationNode, ArgumentsPair> _nodes){
        ArgumentsPair pairSet_ = ExecHelper.getArgumentPair(_nodes, _settable);
        return _settable instanceof ExecCustArrOperation || pairSet_.getWrapper() instanceof ArrayCustWrapper;
    }

    static Argument callIndexer(Argument _right, ArgumentsPair _pair, Argument _stored, boolean _post) {
        Argument out_;
        if (!_pair.isCalledIndexer()) {
            _pair.setCalledIndexer(true);
            out_ = getPrePost(_post, _stored, _right);
        } else {
            out_ = _right;
        }
        return out_;
    }

    static Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, Argument _stored, ExecOperationNode _settable, boolean _staticPostEltContent, StackCall _stackCall) {
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

    public ImplicitMethods getConverterTo() {
        return converterTo;
    }

    static Argument getPrePost(boolean _post, Argument _stored, Argument _right) {
        if (_post) {
            return _stored;
        }
        return _right;
    }

    protected ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    public boolean isPost() {
        return post;
    }

}
