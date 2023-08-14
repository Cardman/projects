package code.threads;

public interface AbstractAtomicIntegerCoreAdd extends AbstractAtomicIntegerCore {

    int getAndAdd(int _value);

    int addAndGet(int _value);
}
