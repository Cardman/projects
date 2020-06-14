package code.expressionlanguage.exec.opers;
import code.expressionlanguage.opers.AndOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecAndOperation extends ExecQuickOperation {

    public ExecAndOperation(AndOperation _a) {
        super(_a);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isFalse(_struct);
    }
}
