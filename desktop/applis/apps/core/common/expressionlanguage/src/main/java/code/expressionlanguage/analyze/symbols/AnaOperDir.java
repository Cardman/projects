package code.expressionlanguage.analyze.symbols;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.structs.Struct;

public final class AnaOperDir implements AnaOperSymbol {
    private final CommonOperSymbol symbol;

    public AnaOperDir(CommonOperSymbol _s) {
        this.symbol = _s;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast, AnalyzedPageEl _cont) {
        return symbol.calculateOperator(_first, _second, _cast);
    }

}
