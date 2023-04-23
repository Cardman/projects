package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.util.CustList;

public final class ResultRowSrcLocationList {
    private final AnalyzedPageEl page;
    private final CustList<RowSrcLocation> symbols;
    public ResultRowSrcLocationList(AnalyzedPageEl _p, CustList<RowSrcLocation> _symb) {
        page = _p;
        symbols = _symb;
    }

    public AnalyzedPageEl getPage() {
        return page;
    }

    public CustList<RowSrcLocation> getSymbols() {
        return symbols;
    }
}
