package code.util;
import code.util.ints.Reversible;


public final class ReversibleNaturalTreeNodeMap<K extends Comparable<K>> extends NaturalTreeNodeMap<K, K> implements Reversible{

    @Override
    public Reversible getReversible() {
        ReversibleNaturalTreeNodeMap<K> reversible_ = new ReversibleNaturalTreeNodeMap<K>();
        return reversible_;
    }
}
