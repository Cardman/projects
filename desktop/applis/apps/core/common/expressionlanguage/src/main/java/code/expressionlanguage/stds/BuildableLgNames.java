package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.analyze.AbstractFieldFilter;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;

public interface BuildableLgNames {
    void build();
    void logIssue(String _info);
    LgNamesContent getContent();

    ContextEl newContext(Options _opt,Forwards _options);
    AbstractFieldFilter newFieldFilter();
    AbstractConstantsCalculator newConstantsCalculator();
}
