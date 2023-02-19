package code.formathtml;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.stds.LoggableLgNames;

public final class RendLoggableSample implements LoggableLgNames {
    @Override
    public String logIssue(String _info, ReportedMessages _rep) {
        return _info;
    }
}
