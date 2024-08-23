package code.expressionlanguage.options;

public final class SuffixedNumber {
    private final String keyWord;
    private final int suffix;

    public SuffixedNumber(String _k, int _v) {
        this.keyWord = _k;
        this.suffix = _v;
    }

    public String getKey() {
        return keyWord;
    }

    public int getValue() {
        return suffix;
    }
}
