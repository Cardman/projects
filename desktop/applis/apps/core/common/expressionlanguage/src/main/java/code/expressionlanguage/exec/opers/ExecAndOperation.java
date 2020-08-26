package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.AndOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecAndOperation extends ExecQuickOperation {

    public ExecAndOperation(AndOperation _a, ContextEl _context) {
        super(_a,_context);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isFalse(_struct);
    }
}
