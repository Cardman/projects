package code.mock;

import code.threads.AbstractAtomicInteger;

public final class MockAtomicInteger implements AbstractAtomicInteger {
    private int integer;
    public MockAtomicInteger() {
        this(0);
    }

    public MockAtomicInteger(int _i) {
        integer = _i;
    }

    @Override
    public int get() {
        return integer;
    }

    @Override
    public void set(int _value) {
        integer = _value;
    }

    @Override
    public boolean compareAndSet(int _one, int _two) {
        if (integer == _one) {
            integer = _two;
            return true;
        }
        return false;
    }

    @Override
    public int getAndSet(int _value) {
        int old_ = integer;
        integer = _value;
        return old_;
    }

    @Override
    public int getAndAdd(int _value) {
        int old_ = integer;
        integer+=_value;
        return old_;
    }

    @Override
    public int addAndGet(int _value) {
        integer+=_value;
        return integer;
    }

    @Override
    public int getAndIncrement() {
        int old_ = integer;
        integer++;
        return old_;
    }

    @Override
    public int incrementAndGet() {
        integer++;
        return integer;
    }

    @Override
    public int getAndDecrement() {
        int old_ = integer;
        integer--;
        return old_;
    }

    @Override
    public int decrementAndGet() {
        integer--;
        return integer;
    }

    @Override
    public void lazySet(int _value) {
        integer = _value;
    }
}
