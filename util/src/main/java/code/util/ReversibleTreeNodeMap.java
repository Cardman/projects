package code.util;
import java.util.Comparator;

import code.util.ints.Reversible;

public final class ReversibleTreeNodeMap<K> extends TreeNodeMap<K, K> implements Reversible{

    public ReversibleTreeNodeMap(Comparator<K> _cmp) {
        super(_cmp);
    }

    @Override
    public Reversible getReversible() {
        ReversibleTreeNodeMap<K> reversible_ = new ReversibleTreeNodeMap<K>(getCmp());
        return reversible_;
    }
}
