package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class ExecCompoundAffectationOperation extends ExecAbstractAffectOperation implements CallExecSimpleOperation,CompoundedOperator {

    private final ImplicitMethods converter;
    private final boolean staticPostEltContent;

    protected ExecCompoundAffectationOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ImplicitMethods _converter, StringList _names, boolean _staticPostEltContent) {
        super(_opCont, _operatorContent, _names);
        converter = _converter;
        staticPostEltContent = _staticPostEltContent;
    }

    @Override
    protected void calculateAffect(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, getSettableAnc());
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        if (argumentPair_.isArgumentTest()){
            pair_.setIndexImplicitConv(-1);
            Argument tow_ = Argument.getNullableValue(argumentPair_.getArgument());
            if (sh(getOperatorContent())) {
                pair_.setEndCalculate(true);
                setSimpleArgument(tow_, _conf, _nodes, _stack);
                return;
            }
            Argument arg_ = calculateChSetting(_nodes, _conf, tow_, _stack);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        calculateSpec(_nodes, _conf, _stack);
    }

    public static boolean sh(ExecOperatorContent _operatorContent) {
        return StringUtil.quickEq(_operatorContent.getOper(), AbsBk.AND_LOG_EQ_SHORT) || StringUtil.quickEq(_operatorContent.getOper(), AbsBk.OR_LOG_EQ_SHORT)|| StringUtil.quickEq(_operatorContent.getOper(), AbsBk.NULL_EQ_SHORT);
    }

    protected abstract void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                          ContextEl _conf, StackCall _stack);

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        end(this,_conf,_nodes,_right,_stack,converter,staticPostEltContent);
    }

    protected static void end(ExecAbstractAffectOperation _current, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack, ImplicitMethods _impl, boolean _post) {
        _current.setRelOffsetPossibleLastPage(_current.getOperatorContent().getOpOffset(), _stack);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,_current);
        Argument stored_ = firstArg(_current,_nodes);
        int indexImplicit_ = pair_.getIndexImplicitConv();
        if (ImplicitMethods.isValidIndex(_impl,indexImplicit_)) {
            pair_.setIndexImplicitConv(ParamCheckerUtil.processConverter(_conf,_right, _impl,indexImplicit_, _stack));
            return;
        }
        if (!pair_.isEndCalculate()) {
            _current.calculateChSetting(_nodes, _conf,_right, _stack);
            Argument arg_ = getPrePost(_post, stored_, _right);
//            Argument arg_ = endCalculate(_conf, _nodes, _right, stored_, _current.getSettable(), _post, _stack);
            _current.setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        if (pair_.isIndexer()) {
            Argument out_ = callIndexer(_right, pair_, stored_, _post);
            _current.setSimpleArgument(out_, _conf, _nodes, _stack);
            return;
        }
        _current.setSimpleArgument(_right, _conf, _nodes, _stack);
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

    static Argument getPrePost(boolean _post, Argument _stored, Argument _right) {
        if (_post) {
            return _stored;
        }
        return _right;
    }

    public boolean isStaticPostEltContent() {
        return staticPostEltContent;
    }

    @Override
    public String getOper() {
        return getOperatorContent().getOper();
    }

    public ImplicitMethods getConverter() {
        return converter;
    }
}
