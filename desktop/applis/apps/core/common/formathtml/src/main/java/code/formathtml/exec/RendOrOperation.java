package code.formathtml.exec;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OrOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class RendOrOperation extends RendQuickOperation {

    public RendOrOperation(OrOperation _o, ContextEl _context) {
        super(_o,_context);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isTrue(_struct);
    }
}
