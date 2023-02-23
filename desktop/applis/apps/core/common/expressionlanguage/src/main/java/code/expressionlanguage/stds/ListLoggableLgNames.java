package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.ReportedMessages;
import code.util.StringList;

public final class ListLoggableLgNames implements LoggableLgNames {
    private final StringList list = new StringList();
    @Override
    public String logIssue(String _info, ReportedMessages _rep) {
        getList().add(_info);
        return _info;
    }

    public StringList getList() {
        return list;
    }
}
