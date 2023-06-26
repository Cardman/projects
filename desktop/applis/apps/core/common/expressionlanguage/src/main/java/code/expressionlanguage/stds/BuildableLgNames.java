package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;

public interface BuildableLgNames {
    void build();
    LgNamesContent getContent();

    AbstractConstantsCalculator newConstantsCalculator();

    CommonExecutionInfos newContextCommon(Options _opt, Forwards _options);
    AbstractInterceptorStdCaller interceptor();
}
