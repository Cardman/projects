package code.threads;

public interface AbstractAtomicBoolean extends AbstractAtomicBooleanCore {

    boolean compareAndSet(boolean _one, boolean _two);

    boolean getAndSet(boolean _value);

    void lazySet(boolean _value);
}
