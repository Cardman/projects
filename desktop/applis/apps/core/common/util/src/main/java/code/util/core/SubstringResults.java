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
            processCandidate(_sub, s);
        }
    }

    private void processCandidate(String _sub, String _s) {
        int locIndex_ = _sub.indexOf(_s);
        if (outBound(minIndex, locIndex_)) {
            return;
        }
        int locLength_ = _s.length();
        if (locLength_ == 0) {
            return;
        }
        if (locIndex_ < minIndex) {
            minIndex = locIndex_;
            maxLength = locLength_;
            candidate = _s;
            return;
        }
        if (locLength_ > maxLength) {
            maxLength = locLength_;
            candidate = _s;
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
