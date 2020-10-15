package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.util.ContextUtil;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultCurrentConstraints implements AbstractCurrentConstraints {
    private final AnalyzedPageEl context;

    public DefaultCurrentConstraints(AnalyzedPageEl _context) {
        this.context = _context;
    }

    @Override
    public StringMap<StringList> getCurrentConstraints() {
        return ContextUtil.getCurrentConstraints(context);
    }
}
