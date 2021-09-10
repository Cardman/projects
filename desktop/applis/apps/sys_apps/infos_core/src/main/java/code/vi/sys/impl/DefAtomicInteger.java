package code.vi.sys.impl;

import code.threads.AbstractAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public final class DefAtomicInteger implements AbstractAtomicInteger {
    private final AtomicInteger intValue;
    public DefAtomicInteger() {
        intValue = new AtomicInteger();
    }
    public DefAtomicInteger(int _value) {
        intValue = new AtomicInteger(_value);
    }

    @Override
    public int get() {
        return intValue.get();
    }

    @Override
    public void set(int _value) {
        intValue.set(_value);
    }

    @Override
    public boolean compareAndSet(int _one, int _two) {
        return intValue.compareAndSet(_one, _two);
    }

    @Override
    public int getAndSet(int _value) {
        return intValue.getAndSet(_value);
    }

    @Override
    public int getAndAdd(int _value) {
        return intValue.getAndAdd(_value);
    }

    @Override
    public int addAndGet(int _value) {
        return intValue.addAndGet(_value);
    }

    @Override
    public int getAndIncrement() {
        return intValue.getAndIncrement();
    }

    @Override
    public int incrementAndGet() {
        return intValue.incrementAndGet();
    }

    @Override
    public int getAndDecrement() {
        return intValue.getAndDecrement();
    }

    @Override
    public int decrementAndGet() {
        return intValue.decrementAndGet();
    }

    @Override
    public void lazySet(int _value) {
        intValue.lazySet(_value);
    }
}
