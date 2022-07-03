package code.threads;

public interface AbstractAtomicBoolean {
    boolean get();

    void set(boolean _value);

    boolean compareAndSet(boolean _one, boolean _two);

    boolean getAndSet(boolean _value);

    void lazySet(boolean _value);
}
