package code.expressionlanguage.stds;

import code.util.StringList;

public class IterableAnalysisResult {

    private StringList className;

    public IterableAnalysisResult(StringList _className) {
        className = _className;
    }
    public StringList getClassName() {
        return className;
    }
}
