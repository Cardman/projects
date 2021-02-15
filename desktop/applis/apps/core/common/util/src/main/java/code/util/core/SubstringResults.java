package code.util.core;

final class SubstringResults {
    private String candidate;
    private int minIndex;
    private int maxLength;
    SubstringResults(int _len) {
        minIndex = _len;
    }
    void calculate(String _sub, String... _separators) {
        for (String s: _separators) {
            int locIndex_ = _sub.indexOf(s);
            if (outBound(minIndex, locIndex_)) {
                continue;
            }
            int locLength_ = s.length();
            if (locLength_ == 0) {
                continue;
            }
            if (locIndex_ < minIndex) {
                minIndex = locIndex_;
                maxLength = locLength_;
                candidate = s;
                continue;
            }
            if (locLength_ > maxLength) {
                maxLength = locLength_;
                candidate = s;
            }
        }
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getMinIndex() {
        return minIndex;
    }

    public String getCandidate() {
        return candidate;
    }

    private static boolean outBound(int _minIndex, int _locIndex) {
        return _locIndex < 0 || _locIndex > _minIndex;
    }
}
