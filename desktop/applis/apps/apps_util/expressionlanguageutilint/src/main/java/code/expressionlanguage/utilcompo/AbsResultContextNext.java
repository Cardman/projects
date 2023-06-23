package code.expressionlanguage.utilcompo;

import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.threads.AbstractAtomicBoolean;

public interface AbsResultContextNext extends AbsLightResultContextNext {
    ResultContext init(Options _opt);

    ResultContext next(ResultContext _r, ResultContext _u);
    AbsAdvContextGenerator generate();
    AbsAdvContextGenerator generate(AbstractAtomicBoolean _at);
}
