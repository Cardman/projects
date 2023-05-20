package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.util.CustList;

public final class ResultRowSrcLocationList {
    private final AnalyzedPageEl page;
    private final CustList<SrcFileLocation> usages;
    private final CustList<RowSrcLocation> symbols;
    public ResultRowSrcLocationList(AnalyzedPageEl _p, CustList<SrcFileLocation> _us, CustList<RowSrcLocation> _symb) {
        page = _p;
        usages = _us;
        symbols = _symb;
    }

    public AnalyzedPageEl getPage() {
        return page;
    }

    public CustList<SrcFileLocation> getUsages() {
        return usages;
    }

    public CustList<RowSrcLocation> getSymbols() {
        return symbols;
    }
}
