package code.expressionlanguage.sample;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.stds.LoggableLgNames;

public final class ElLoggableSample implements LoggableLgNames {
    @Override
    public String logIssue(String _info, ReportedMessages _rep) {
        return _info;
    }
}
