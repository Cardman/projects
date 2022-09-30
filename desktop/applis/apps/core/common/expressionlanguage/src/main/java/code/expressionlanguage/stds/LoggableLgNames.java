package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;

public interface LoggableLgNames {
    void logIssue(String _info, ReportedMessages _rep);
    ContextEl newContext(Options _opt, Forwards _options);
}
