package code.formathtml;

public final class InterruptValue implements Interrupt {
    private boolean value;
    @Override
    public void set(boolean _b) {
        value = _b;
    }

    @Override
    public boolean get() {
        return value;
    }
}
