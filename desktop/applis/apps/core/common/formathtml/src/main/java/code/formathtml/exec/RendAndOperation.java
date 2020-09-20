package code.formathtml.exec;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AndOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class RendAndOperation extends RendQuickOperation {

    public RendAndOperation(AndOperation _a, AnalyzedPageEl _page) {
        super(_a, _page);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isFalse(_struct);
    }
}
