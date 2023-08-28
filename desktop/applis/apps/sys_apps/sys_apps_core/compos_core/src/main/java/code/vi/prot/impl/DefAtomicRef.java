package code.vi.prot.impl;

import code.threads.AbstractAtomicRef;

import java.util.concurrent.atomic.AtomicReference;

public final class DefAtomicRef<T> implements AbstractAtomicRef<T> {
    private final AtomicReference<T> intValue;
    public DefAtomicRef() {
        intValue = new AtomicReference<T>();
    }
    public DefAtomicRef(T _value) {
        intValue = new AtomicReference<T>(_value);
    }

    @Override
    public T get() {
        return intValue.get();
    }

    @Override
    public void set(T _value) {
        intValue.set(_value);
    }

    @Override
    public T getAndSet(T _value) {
        return intValue.getAndSet(_value);
    }

    @Override
    public void lazySet(T _value) {
        intValue.lazySet(_value);
    }
}
