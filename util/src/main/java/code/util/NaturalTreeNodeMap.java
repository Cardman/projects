package code.util;
import code.util.comparators.NaturalComparatorList;
import code.util.ints.Listable;


public class NaturalTreeNodeMap<K extends Comparable<K>, V> {

    private final TreeMap<SortableList<K>, V> tree;

    public NaturalTreeNodeMap() {
        tree = new TreeMap<SortableList<K>, V>(new NaturalComparatorList<K>());
    }

    public EqList<SortableList<K>> getChildren(SortableList<K> _node) {
        EqList<SortableList<K>> children_ = new EqList<SortableList<K>>();
        for (SortableList<K> k: tree.getKeys()) {
            if (k.size() != _node.size() + 1) {
                continue;
            }
            SortableList<K> subList_ = k.sub(0, _node.size());
            if (!subList_.eq(_node)) {
                continue;
            }
            children_.add(k);
        }
        return children_;
    }

    public Listable<K> getRoot() {
        if (!tree.isEmpty()) {
            return tree.firstKey();
        }
        return null;
    }
    public Listable<K> getParent(Listable<K> _path) {
        if (_path.isEmpty()) {
            return null;
        }
        return _path.sub(0, _path.size() - 1);
    }
    public CustList<Listable<K>> getPaths(K _node) {
        CustList<Listable<K>> paths_ = new CustList<Listable<K>>();
        for (Listable<K> k: tree.getKeys()) {
            int i_ = 0;
            for (K s: k) {
                if (s.compareTo(_node) != 0) {
                    i_++;
                    continue;
                }
                paths_.add(k.sub(0, i_));
                i_++;
            }
        }
        return paths_;
    }

}
