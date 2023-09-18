package code.expressionlanguage.analyze.symbols;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.common.symbol.SymbolConstants;
import code.expressionlanguage.structs.Struct;

public final class AnaOperDir implements AnaOperSymbol {
    private final CommonOperSymbol symbol;

    public AnaOperDir(CommonOperSymbol _s) {
        this.symbol = _s;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, AnalyzedPageEl _cont) {
        return SymbolConstants.calculateOperator(symbol,_first, _second);
    }

}
