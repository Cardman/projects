package code.formathtml.exec;
import code.expressionlanguage.opers.AndOperation;
import code.expressionlanguage.structs.BooleanStruct;

public final class RendAndOperation extends RendQuickOperation {

    public RendAndOperation(AndOperation _a) {
        super(_a);
    }

    @Override
    public BooleanStruct absorbingStruct() {
        return new BooleanStruct(false);
    }

}
