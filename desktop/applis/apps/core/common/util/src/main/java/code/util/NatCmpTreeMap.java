package code.util;
import code.util.ints.Cmp;

/**
    @author Cardman
*/
public final class NatCmpTreeMap<K extends Cmp<K>, V> extends AbsBasicTreeMap<K, V> {

    @Override
    protected int compare(K _one, K _two) {
        return _one.cmp(_two);
    }
}
