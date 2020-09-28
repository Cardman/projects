package code.formathtml.analyze;

import code.expressionlanguage.analyze.AbstractCurrentConstraints;
import code.util.StringList;
import code.util.StringMap;

public final class AdvancedCurrentConstraints implements AbstractCurrentConstraints {

    @Override
    public StringMap<StringList> getCurrentConstraints() {
        return new StringMap<StringList>();
    }
}
