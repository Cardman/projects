package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;

public interface BuildableLgNames {
    void build();
    LgNamesContent getContent();

    AbstractConstantsCalculator newConstantsCalculator();
    ContextEl newContext(Options _opt, Forwards _options);
}
