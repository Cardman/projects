package aiki.gui.components.editor;

import code.gui.AbsDefValue;

public final class NbDefValue<T> implements AbsDefValue<T> {
    private final T def;

    public NbDefValue(T _d) {
        this.def = _d;
    }

    @Override
    public T defValue() {
        return def;
    }
}
