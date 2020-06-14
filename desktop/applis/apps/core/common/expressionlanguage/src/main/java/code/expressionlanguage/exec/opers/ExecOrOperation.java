package code.expressionlanguage.exec.opers;
import code.expressionlanguage.opers.OrOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecOrOperation extends ExecQuickOperation {

    public ExecOrOperation(OrOperation _o) {
        super(_o);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isTrue(_struct);
    }
}
