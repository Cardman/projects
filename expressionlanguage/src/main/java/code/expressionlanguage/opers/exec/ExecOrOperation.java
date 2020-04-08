package code.expressionlanguage.opers.exec;
import code.expressionlanguage.opers.OrOperation;
import code.expressionlanguage.structs.BooleanStruct;

public final class ExecOrOperation extends ExecQuickOperation {

    public ExecOrOperation(OrOperation _o) {
        super(_o);
    }

    @Override
    public BooleanStruct absorbingStruct() {
        return BooleanStruct.of(true);
    }
}
