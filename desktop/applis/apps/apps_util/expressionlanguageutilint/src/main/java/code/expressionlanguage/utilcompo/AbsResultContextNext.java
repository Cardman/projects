package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.threads.AbstractAtomicBoolean;
import code.util.StringMap;

public interface AbsResultContextNext extends AbsLightResultContextNext {
    StringMap<String> files(ResultContext _r, StringMap<String> _files);
    ResultContext init(Options _opt);

    AnalyzedPageEl nextAna(ResultContext _r, StringMap<String> _files);
    ResultContext next(ResultContext _r, ResultContext _u);
    AbsAdvContextGenerator generate();
    AbsAdvContextGenerator generate(AbstractAtomicBoolean _at);
}
