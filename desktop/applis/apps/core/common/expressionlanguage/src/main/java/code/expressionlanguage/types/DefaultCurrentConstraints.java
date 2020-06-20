package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultCurrentConstraints implements AbstractCurrentConstraints {
    private final ContextEl context;

    public DefaultCurrentConstraints(ContextEl context) {
        this.context = context;
    }

    @Override
    public StringMap<StringList> getCurrentConstraints() {
        return ContextUtil.getCurrentConstraints(context);
    }
}
