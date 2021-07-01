package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;


public abstract class ExecQuickOperation extends ExecMethodOperation implements AtomicExecCalculableOperation, CallExecSimpleOperation {

    private final Struct absorbingValue;
    private final ImplicitMethods converter;

    protected ExecQuickOperation(ExecOperationContent _opCont, ImplicitMethods _converter, boolean _absorbingValue) {
        super(_opCont);
        converter = _converter;
        absorbingValue = BooleanStruct.of(_absorbingValue);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        setRelativeOffsetPossibleLastPage(_stack);
        ExecOperationNode first_ = getFirstChild();
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            setSimpleArgument(_right,_conf,_nodes, _stack);
            return;
        }
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        int indexImplicit_ = pair_.getIndexImplicitCompound();
        if (ImplicitMethods.isValidIndex(converter,indexImplicit_)) {
            pair_.setIndexImplicitCompound(processConverter(_conf, _right, converter, indexImplicit_, _stack));
            return;
        }
        setSimpleArgument(_right,_conf,_nodes, _stack);
    }
    public Struct getAbsorbingValue() {
        return absorbingValue;
    }

    public boolean match(Struct _struct){
        return absorbingValue.sameReference(_struct);
    }
}
