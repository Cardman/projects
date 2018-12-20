package code.expressionlanguage.opers.exec;
import code.expressionlanguage.opers.AndOperation;
import code.expressionlanguage.structs.BooleanStruct;

public final class ExecAndOperation extends ExecQuickOperation {

    public ExecAndOperation(AndOperation _a) {
        super(_a);
    }

    @Override
    boolean absorbingValue() {
        return false;
    }

    @Override
    public BooleanStruct absorbingStruct() {
        return new BooleanStruct(false);
    }

}
