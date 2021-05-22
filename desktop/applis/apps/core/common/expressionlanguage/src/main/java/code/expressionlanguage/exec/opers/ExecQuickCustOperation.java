package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.util.IdMap;


public final class ExecQuickCustOperation extends ExecQuickOperation implements CallExecSimpleOperation {

    private final ExecStaticEltContent staticEltContent;
    private final ImplicitMethods converter;
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    public ExecQuickCustOperation(ExecOperationContent _opCont, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, boolean _absorbingValue) {
        super(_opCont, _absorbingValue);
        staticEltContent = _staticEltContent;
        pair = _pair;
        converter = _converter;
        formattedType = _staticEltContent.getFormattedType();
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf, StackCall _stack) {
        ExecOperationNode first_ = getFirstChild();
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            Argument f_ = getArgument(_nodes,first_);
            setQuickConvertSimpleArgument(f_, _conf, _nodes, _stack);
            return;
        }
        setRelativeOffsetPossibleLastPage(_stack);
        checkParametersOperators(_conf.getExiting(),_conf, pair, _nodes, formattedType, staticEltContent.getKind(), _stack);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ExecOperationNode first_ = getFirstChild();
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            setSimpleArgument(_right,_conf,_nodes, _stack);
            return;
        }
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        setRelativeOffsetPossibleLastPage(_stack);
        ImplicitMethods implicits_ = pair_.getImplicitsCompound();
        int indexImplicit_ = pair_.getIndexImplicitCompound();
        if (implicits_.isValidIndex(indexImplicit_)) {
            pair_.setIndexImplicitCompound(processConverter(_conf, _right, implicits_, indexImplicit_, _stack));
            return;
        }
        setSimpleArgument(_right,_conf,_nodes, _stack);
    }

    public ImplicitMethods getConverter() {
        return converter;
    }
}
