package code.expressionlanguage.opers.exec;
import code.expressionlanguage.opers.AndOperation;
import code.expressionlanguage.structs.BooleanStruct;

public final class ExecAndOperation extends ExecQuickOperation {

    public ExecAndOperation(AndOperation _a) {
        super(_a);
    }

    @Override
    public BooleanStruct absorbingStruct() {
        return BooleanStruct.of(false);
    }

}
