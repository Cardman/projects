package code.expressionlanguage.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultCurrentConstraints implements AbstractCurrentConstraints {
    private final AnalyzedPageEl context;

    public DefaultCurrentConstraints(AnalyzedPageEl context) {
        this.context = context;
    }

    @Override
    public StringMap<StringList> getCurrentConstraints() {
        return ContextUtil.getCurrentConstraints(context);
    }
}
