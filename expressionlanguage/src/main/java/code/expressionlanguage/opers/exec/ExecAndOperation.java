package code.expressionlanguage.opers.exec;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.AndOperation;
import code.expressionlanguage.opers.QuickOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecAndOperation extends ExecQuickOperation {

    public ExecAndOperation(AndOperation _a) {
        super(_a);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        QuickOperation.tryGetResult(_conf, this, false,true);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isFalse(_struct);
    }
}
