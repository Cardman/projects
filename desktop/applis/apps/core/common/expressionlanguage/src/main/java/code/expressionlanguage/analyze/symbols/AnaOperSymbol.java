package code.expressionlanguage.analyze.symbols;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.structs.Struct;

public interface AnaOperSymbol {
    Struct calculateOperator(Struct _first, Struct _second, byte _cast, AnalyzedPageEl _cont);
}
