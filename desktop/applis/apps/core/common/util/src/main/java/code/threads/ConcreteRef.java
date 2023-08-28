package code.threads;

public final class ConcreteRef<T> implements AbstractAtomicRef<T> {
    private T value;
    public ConcreteRef() {
        this(null);
    }
    public ConcreteRef(T _v) {
        this.value = _v;
    }
    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T _value) {
        this.value = _value;
    }

//    @Override
//    public boolean compareAndSet(T _one, T _two) {
//        if (this.value == _one) {
//            this.value = _two;
//            return true;
//        }
//        return false;
//    }

    @Override
    public T getAndSet(T _value) {
        T old_ = this.value;
        this.value = _value;
        return old_;
    }

    @Override
    public void lazySet(T _value) {
        this.value = _value;
    }
}
