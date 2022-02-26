package code.expressionlanguage.options;

public final class SuffixedNumber {
    private final String keyWord;
    private final char suffix;

    public SuffixedNumber(String _k, char _v) {
        this.keyWord = _k;
        this.suffix = _v;
    }

    public String getKey() {
        return keyWord;
    }

    public char getValue() {
        return suffix;
    }
}
