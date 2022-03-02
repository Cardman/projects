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

    @Override
    public void endCalculate(ContextEl _conf,
                             IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        end(this,_conf, _nodes, _right, _stack,converterTo,post);
    }

    protected static void end(ExecAbstractAffectOperation _current,ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack, ImplicitMethods _impl, boolean _post) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,_current);
        Argument stored_ = firstArg(_current,_nodes);
        int indexImplicit_ = pair_.getIndexImplicitConv();
        if (ImplicitMethods.isValidIndex(_impl,indexImplicit_)) {
            pair_.setIndexImplicitConv(ExecOperationNode.processConverter(_conf,_right, _impl,indexImplicit_, _stack));
            return;
        }
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            _current.calculateChSetting(_nodes, _conf,_right, _stack);
            Argument arg_ = ExecSemiAffectationOperation.getPrePost(_post, stored_, _right);
//            Argument arg_ = endCalculate(_conf, _nodes, _right, stored_, _current.getSettable(), _post, _stack);
            _current.setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        if (isIndexer(_current.getSettable(),_nodes)) {
            Argument out_ = callIndexer(_right, pair_, stored_, _post);
            _current.setSimpleArgument(out_, _conf, _nodes, _stack);
            return;
        }
        _current.setSimpleArgument(_right, _conf, _nodes, _stack);
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
