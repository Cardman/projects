package code.expressionlanguage.common;

public final class MinCaseCharMapping implements AbsCharMapping {
    @Override
    public char map(char _ch) {
        return (char) NumParsers.toMinCase(_ch);
    }
}
