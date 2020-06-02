package code.formathtml.exec;
import code.expressionlanguage.opers.AndOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class RendAndOperation extends RendQuickOperation {

    public RendAndOperation(AndOperation _a) {
        super(_a);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isFalse(_struct);
    }
}
