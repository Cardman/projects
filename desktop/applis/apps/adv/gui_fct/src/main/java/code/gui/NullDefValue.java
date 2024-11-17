package code.gui;

public final class NullDefValue<T> implements AbsDefValue<T> {
    @Override
    public T defValue() {
        return null;
    }
}
