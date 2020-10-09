package code.util;

/**
    @author Cardman
*/
public final class NatStringTreeMap<V> extends AbsBasicTreeMap<String, V> {

    @Override
    protected int compare(String _one, String _two) {
        return _one.compareTo(_two);
    }
}
