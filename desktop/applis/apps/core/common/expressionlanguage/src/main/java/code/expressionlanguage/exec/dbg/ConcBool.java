package code.expressionlanguage.exec.dbg;

public final class ConcBool implements AbsAtBool {
    private boolean val;
    @Override
    public boolean get() {
        return val;
    }

    @Override
    public void set(boolean _s) {
        val = _s;
    }
}
