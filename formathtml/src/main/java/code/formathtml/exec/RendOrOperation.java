package code.formathtml.exec;
import code.expressionlanguage.opers.OrOperation;
import code.expressionlanguage.structs.BooleanStruct;

public final class RendOrOperation extends RendQuickOperation {

    public RendOrOperation(OrOperation _o) {
        super(_o);
    }

    @Override
    public BooleanStruct absorbingStruct() {
        return new BooleanStruct(true);
    }
}
