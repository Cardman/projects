package code.expressionlanguage.analyze.util;

import code.util.StringList;

public final class IterableAnalysisResult {

    private StringList className;

    public IterableAnalysisResult(StringList _className) {
        className = _className;
    }
    public StringList getClassName() {
        return className;
    }
}
