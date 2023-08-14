package code.threads;

public interface AbstractAtomicInteger extends AbstractAtomicIntegerCoreAdd {

    boolean compareAndSet(int _one, int _two);

    int getAndSet(int _value);

    int getAndIncrement();

    int incrementAndGet();

    int getAndDecrement();

    int decrementAndGet();

    void lazySet(int _value);
}
