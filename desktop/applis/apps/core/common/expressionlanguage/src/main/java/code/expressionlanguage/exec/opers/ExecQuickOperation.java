package code.expressionlanguage.exec.opers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;


public abstract class ExecQuickOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private final Struct absorbingValue;

    protected ExecQuickOperation(ExecOperationContent _opCont, boolean _absorbingValue) {
        super(_opCont);
        absorbingValue = BooleanStruct.of(_absorbingValue);
    }

    public Struct getAbsorbingValue() {
        return absorbingValue;
    }

    public boolean match(Struct _struct){
        return absorbingValue.sameReference(_struct);
    }
}
