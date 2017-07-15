package code.util;
import java.util.Comparator;

import code.util.annot.RwXml;
import code.util.comparators.ComparatorList;
import code.util.ints.Listable;

@RwXml
public class TreeNodeMap<K,V> {

    private final TreeMap<AbEqList<K>,V> tree;

    private final Comparator<K> cmp;

    public TreeNodeMap() {
        tree = new TreeMap<AbEqList<K>,V>();
        cmp = null;
    }

    public TreeNodeMap(Comparator<K> _cmp) {
        tree = new TreeMap<AbEqList<K>,V>(new ComparatorList<K>(_cmp));
        cmp = _cmp;
    }

    public Comparator<K> getCmp() {
        return cmp;
    }

//    public CustList<AbEqList<K>> getChildren(AbEqList<K> _node) {
//        CustList<AbEqList<K>> children_ = new CustList<AbEqList<K>>();
//        for (AbEqList<K> k: tree.getKeys()) {
//            if (k.size() != _node.size() + 1) {
//                continue;
//            }
//            AbEqList<K> subList_ = k.subAbEq(0, _node.size());
//            if (!subList_.eq(_node)) {
//                continue;
//            }
//            children_.add(k);
//        }
//        return children_;
//    }

    public Listable<K> getRoot() {
        if (!tree.isEmpty()) {
            return tree.firstKey();
        }
        return null;
    }
    public CustList<K> getParent(CustList<K> _path) {
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
                if (cmp.compare(s, _node) != 0) {
                    i_++;
                    continue;
                }
                paths_.add(k.sub(0, i_));
                i_++;
            }
        }
        return paths_;
    }

//    @Override
//    public void putAllMap(ListableEntries<? extends Listable<K>, ? extends V> _m) {
//        putAll(_m);
//    }
}

