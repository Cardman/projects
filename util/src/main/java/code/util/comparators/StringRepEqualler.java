package code.util.comparators;

import code.util.StringList;
import code.util.ints.Equaller;

public class StringRepEqualler<T> implements Equaller<T> {

    @Override
    public boolean eq(T _one, T _two) {
        if (_one == null) {
            return _two == null;
        }
        return StringList.quickEq(_one.toString(), _two.toString());
    }

}
