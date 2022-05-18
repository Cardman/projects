package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.IdMap;

public final class ExecExplicitOperatorOperation extends ExecSettableCallFctOperation implements CallExecSimpleOperation,CompoundedOperator{

    private final ImplicitMethods converter;
    private final ExecTypeFunction pair;
    private final ExecStaticFctContent staticFctContent;

    private final ExecOperatorContent operatorContent;
    public ExecExplicitOperatorOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecTypeFunction _pair, ExecArrContent _exArr, ImplicitMethods _conv, ExecOperatorContent _operCont) {
        super(_opCont, _intermediateDottedOperation,_exArr);
        staticFctContent = _staticFctContent;
        pair = _pair;
        converter = _conv;
        operatorContent = _operCont;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stack);
        ExecOperationNode first_ = getFirstChild();
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            Argument f_ = getArgument(_nodes,first_);
            setQuickConvertSimpleArgument(f_, _conf, _nodes, _stack);
            return;
        }
        checkParametersOperatorsFormatted(this,pair,staticFctContent,_nodes, _conf, _stack);
    }

    static void checkParametersOperatorsFormatted(ExecMethodOperation _curr, ExecTypeFunction _pair, ExecStaticFctContent _elt,IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecFormattedRootBlock classNameFound_ = ExecFormattedRootBlock.formatType(_elt.getElts(), _stack);
        String lastType_ = ExecFormattedRootBlock.formatLastType(classNameFound_, _elt);
        ParamCheckerUtil.checkParametersOperatorsFormatted(_conf.getExiting(), _conf, _pair, fectchArgs(lastType_, _elt.getNaturalVararg(), _conf, _stack, _curr.buildInfos(_nodes)), classNameFound_, _elt.getKind(), _stack);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ExecQuickOperation.end(this,_conf,_nodes,_right,_stack,converter);
    }

    public int getOffsetOper() {
        return operatorContent.getOpOffset();
    }

    @Override
    public String getOper() {
        return operatorContent.getOper();
    }
}
