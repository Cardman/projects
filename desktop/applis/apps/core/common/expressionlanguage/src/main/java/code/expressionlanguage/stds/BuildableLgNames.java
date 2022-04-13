package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;

public interface BuildableLgNames {
    void build();
    void logIssue(String _info, ReportedMessages _rep);
    LgNamesContent getContent();

    ContextEl newContext(Options _opt,Forwards _options);

    AbstractConstantsCalculator newConstantsCalculator();
}
