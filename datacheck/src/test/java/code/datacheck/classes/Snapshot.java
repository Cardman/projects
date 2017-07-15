/**
    */
package code.datacheck.classes;
import code.util.ints.Settable;

/**
 */
public class Snapshot<T> extends UnmodifiedList<T> implements Settable<T> {

    /**
    */
    public Snapshot() {
    }

    @Override
    public boolean addEl(T _o) {
        add(_o);
        return false;
    }
}
