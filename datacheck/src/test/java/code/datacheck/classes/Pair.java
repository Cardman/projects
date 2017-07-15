package code.datacheck.classes;
import code.util.annot.RwXml;


@RwXml
public final class Pair<T,S> {

    private static final String SEPARATOR = " ";

    private T first;

    private S second;

    public Pair() {

    }
    public Pair(T _first,S _second) {
        first = _first;
        second = _second;
    }
    public Pair(Pair<T,S> _other) {
        first = _other.first;
        second = _other.second;
    }

    public boolean isInstanceNotNull(Class<?> _class1, Class<?> _class2) {
        try {
            first.getClass().asSubclass(_class1);
        } catch (RuntimeException _0) {
            return false;
        }
        try {
            second.getClass().asSubclass(_class2);
        } catch (RuntimeException _0) {
            return false;
        }
        return true;
    }
    public boolean isInstance(Class<?> _class1, Class<?> _class2) {
        if (first != null) {
            try {
                first.getClass().asSubclass(_class1);
            } catch (RuntimeException _0) {
                return false;
            }
        }
        if (second != null) {
            try {
                second.getClass().asSubclass(_class2);
            } catch (RuntimeException _0) {
                return false;
            }
        }
        return true;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T _first) {
        first = _first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S _second) {
        second = _second;
    }

    @Override
    public String toString() {
        return first+SEPARATOR+second;
    }
}
