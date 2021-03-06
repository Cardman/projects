package code.maths.litteraladv;

public final class MaIndexStackOperator {
    private final int ind;
    private final char oper;

    public MaIndexStackOperator(int _ind, char _oper) {
        this.ind = _ind;
        this.oper = _oper;
    }

    public int getInd() {
        return ind;
    }

    public char getOper() {
        return oper;
    }
}
