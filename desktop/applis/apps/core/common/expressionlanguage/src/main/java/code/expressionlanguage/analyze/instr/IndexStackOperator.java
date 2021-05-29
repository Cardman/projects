package code.expressionlanguage.analyze.instr;

public final class IndexStackOperator {
    private final int index;
    private final char operator;

    public IndexStackOperator(int _index, char _operator) {
        this.index = _index;
        this.operator = _operator;
    }

    public int getIndex() {
        return index;
    }

    public char getOperator() {
        return operator;
    }
}
