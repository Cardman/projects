package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.symbols.ExecOperSymbol;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;


public final class ExecQuickOperation extends ExecMethodOperation implements AtomicExecCalculableOperation, CallExecSimpleOperation, CompoundedOperator {

    private final ImplicitMethods converter = new ImplicitMethods();
    private final ExecOperatorContent operatorContent;

    private final ExecOperSymbol operSymbol;

    public ExecQuickOperation(ExecOperationContent _opCont, ExecOperatorContent _opera, ExecOperSymbol _op) {
        super(_opCont);
        operatorContent = _opera;
        operSymbol = _op;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        ExecOperationNode first_ = getFirstChild();
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            Struct f_ = getArgument(_nodes,first_);
            setQuickConvertSimpleArgument(f_, _conf, _nodes, _stack);
            return;
        }
        Struct f_ = getArgument(_nodes,first_);
        Struct a_ = getLastArgument(_nodes,this);
        setSimpleArgument(ExecCompoundAffectationStringOperation.calculatedValue(operSymbol, f_,a_, _conf, _stack,_stack.getLastPage()), _conf, _nodes, _stack);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Struct _right, StackCall _stack) {
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        end(this,_conf, _nodes, _right, _stack, converter);
    }

    static void end(ExecMethodOperation _cur,ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Struct _right, StackCall _stack, ImplicitMethods _converter) {
        ExecOperationNode first_ = _cur.getFirstChild();
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            _cur.setSimpleArgument(_right,_conf,_nodes, _stack);
            return;
        }
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,_cur);
        int indexImplicit_ = pair_.getIndexImplicitConv();
        if (ImplicitMethods.isValidIndex(_converter,indexImplicit_)) {
            pair_.setIndexImplicitConv(ParamCheckerUtil.processConverter(_conf, _right, _converter, indexImplicit_, _stack));
            return;
        }
        _cur.setSimpleArgument(_right,_conf,_nodes, _stack);
    }

    public ImplicitMethods getConverter() {
        return converter;
    }

    public ExecOperSymbol getOperSymbol() {
        return operSymbol;
    }

    public ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    @Override
    public String getOper() {
        return operatorContent.getOper();
    }
}
