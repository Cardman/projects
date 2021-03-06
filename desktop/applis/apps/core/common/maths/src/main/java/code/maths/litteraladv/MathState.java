package code.maths.litteraladv;

public final class MathState {
    private int index;
    private final MaStackOperators stack = new MaStackOperators();

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public MaStackOperators getStack() {
        return stack;
    }
}
