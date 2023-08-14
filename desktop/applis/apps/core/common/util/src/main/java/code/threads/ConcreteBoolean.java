package code.threads;

public final class ConcreteBoolean implements AbstractAtomicBoolean{
    private boolean value;
    public ConcreteBoolean() {
        this(false);
    }
    public ConcreteBoolean(boolean _v) {
        value = _v;
    }
    @Override
    public boolean get() {
        return value;
    }

    @Override
    public void set(boolean _value) {
        value = _value;
    }

    @Override
    public boolean compareAndSet(boolean _one, boolean _two) {
        if (value == _one) {
            value = _two;
            return true;
        }
        return false;
    }

    @Override
    public boolean getAndSet(boolean _value) {
        boolean old_ = value;
        value = _value;
        return old_;
    }

    @Override
    public void lazySet(boolean _value) {
        value = _value;
    }
}
