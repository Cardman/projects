package code.threads;

public interface AbstractAtomicRef<T> {
    T get();

    void set(T _value);

//    boolean compareAndSet(T _one, T _two);

    T getAndSet(T _value);

    void lazySet(T _value);
}
