package code.expressionlanguage.opers.exec;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.OrOperation;
import code.expressionlanguage.opers.QuickOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecOrOperation extends ExecQuickOperation {

    public ExecOrOperation(OrOperation _o) {
        super(_o);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        QuickOperation.tryGetResult(_conf, this, true,true);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isTrue(_struct);
    }
}
