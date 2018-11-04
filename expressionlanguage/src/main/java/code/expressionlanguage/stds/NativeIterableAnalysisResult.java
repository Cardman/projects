package code.expressionlanguage.stds;

import code.util.StringList;

public final class NativeIterableAnalysisResult extends IterableAnalysisResult {

    private Boolean nativeIterator;

    public NativeIterableAnalysisResult(StringList _className, Boolean _nativeIterator) {
        super(_className);
        nativeIterator = _nativeIterator;
    }
    public Boolean getNativeIterator() {
        return nativeIterator;
    }
}
