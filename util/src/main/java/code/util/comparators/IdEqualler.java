package code.util.comparators;

import code.util.ints.Equaller;

public final class IdEqualler<T> implements Equaller<T> {

    @Override
    public boolean eq(T _one, T _two) {
        return _one == _two;
    }

}
