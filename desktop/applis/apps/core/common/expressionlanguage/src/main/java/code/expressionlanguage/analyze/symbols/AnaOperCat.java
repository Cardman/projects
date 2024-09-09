package code.expressionlanguage.analyze.symbols;

import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.structs.Struct;

public final class AnaOperCat implements AnaOperSymbol {

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, AnalyzedPageEl _cont) {
        return AnaApplyCoreMethodUtil.localSumDiff(_first,_second,_cont);
    }

}
