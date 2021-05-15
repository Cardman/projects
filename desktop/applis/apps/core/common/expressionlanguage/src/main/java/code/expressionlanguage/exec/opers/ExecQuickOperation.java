package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;


public abstract class ExecQuickOperation extends ExecMethodOperation implements AtomicExecCalculableOperation,CallExecSimpleOperation {

    private final ExecStaticEltContent staticEltContent;
    private final ImplicitMethods converter;
    private final int opOffset;
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    protected ExecQuickOperation(ExecOperationContent _opCont, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, int _opOffset) {
        super(_opCont);
        staticEltContent = _staticEltContent;
        pair = _pair;
        converter = _converter;
        opOffset = _opOffset;
        formattedType = _staticEltContent.getFormattedType();
    }
    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf, StackCall _stack) {
        ExecOperationNode first_ = getFirstChild();
        if (pair.getFct() != null) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, first_);
            if (argumentPair_.isArgumentTest()){
                Argument f_ = getArgument(_nodes,first_);
                setQuickConvertSimpleArgument(f_, _conf, _nodes, _stack);
                return;
            }
            setRelativeOffsetPossibleLastPage(_stack);
            checkParametersOperators(_conf.getExiting(),_conf, pair, _nodes, formattedType, staticEltContent.getKind(), _stack);
            return;
        }
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _conf, _nodes, _stack);
            return;
        }
        setRelativeOffsetPossibleLastPage(opOffset, _stack);
        Argument a_ = getLastArgument(_nodes,this);
        setSimpleArgument(a_, _conf, _nodes, _stack);
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

    public abstract boolean match(Struct _struct);
}
