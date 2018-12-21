package code.formathtml.exec;
import code.expressionlanguage.opers.OrOperation;
import code.expressionlanguage.structs.BooleanStruct;

public final class ExecOrOperation extends ExecQuickOperation {

    public ExecOrOperation(OrOperation _o) {
        super(_o);
    }

    @Override
    boolean absorbingValue() {
        return true;
    }

    @Override
    public BooleanStruct absorbingStruct() {
        return new BooleanStruct(true);
    }
}
