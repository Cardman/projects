package code.vi.sys.impl;

import code.threads.AbstractAtomicBoolean;

import java.util.concurrent.atomic.AtomicBoolean;

public final class DefAtomicBoolean implements AbstractAtomicBoolean {
    private final AtomicBoolean booleanValue;
    public DefAtomicBoolean() {
        booleanValue = new AtomicBoolean();
    }
    public DefAtomicBoolean(boolean _value) {
        booleanValue = new AtomicBoolean(_value);
    }

    @Override
    public boolean get() {
        return booleanValue.get();
    }

    @Override
    public void set(boolean _value) {
        booleanValue.set(_value);
    }

    @Override
    public boolean compareAndSet(boolean _one, boolean _two) {
        return booleanValue.compareAndSet(_one, _two);
    }

    @Override
    public boolean getAndSet(boolean _value) {
        return booleanValue.getAndSet(_value);
    }

    @Override
    public void lazySet(boolean _value) {
        booleanValue.lazySet(_value);
    }
}
