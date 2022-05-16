package code.expressionlanguage.analyze.symbols;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.structs.Struct;

public final class AnaOperCat implements AnaOperSymbol {

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast, AnalyzedPageEl _cont) {
        return AnaApplyCoreMethodUtil.localSumDiff(new Argument(_first),new Argument(_second),_cont).getStruct();
    }

}
