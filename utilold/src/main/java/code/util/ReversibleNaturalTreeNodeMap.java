package code.util;


public final class ReversibleNaturalTreeNodeMap<K extends Comparable<K>> extends NaturalTreeNodeMap<K, K> implements Reversible{

    @Override
    public Reversible getReversible() {
        ReversibleNaturalTreeNodeMap<K> reversible_ = new ReversibleNaturalTreeNodeMap<K>();
        return reversible_;
    }
}
