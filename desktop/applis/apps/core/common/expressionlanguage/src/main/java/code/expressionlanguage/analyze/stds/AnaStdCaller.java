package code.expressionlanguage.analyze.stds;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public interface AnaStdCaller extends StdCaller {
    Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args);
}
