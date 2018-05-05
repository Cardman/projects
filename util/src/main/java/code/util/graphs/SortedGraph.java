package code.util.graphs;

import code.util.CustList;
import code.util.EqList;
import code.util.ints.SortedEdge;

public final class SortedGraph<T extends SortedEdge<T>> {

    private Graph<T> graph = new Graph<T>();

    public EqList<T> process() {
        EqList<T> elts_ = graph.getElementsListCopy();
        int order_ = 0;
        while (true) {
            EqList<T> next_ = new EqList<T>();
            for (T e: elts_) {
                if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                    continue;
                }
                EqList<T> list_ = graph.getChildren(e);
                boolean allNb_ = true;
                for (T s: list_) {
                    T s_ = graph.getElementByEq(s);
                    if (s_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                        allNb_ = false;
                        break;
                    }
                }
                if (!allNb_) {
                    continue;
                }
                next_.add(e);
            }
            if (next_.isEmpty()) {
                break;
            }
            for (T o: next_) {
                o.setOrder(order_);
                order_++;
            }
        }
        elts_.sortElts(new EdgeComparator<T>());
        return elts_;
    }

    public EqList<T> getTreeFrom(T _elt) {
        return graph.getTreeFrom(_elt);
    }

    public CustList<ArrowedSegment<T>> getSegments() {
        return graph.getSegments();
    }

    public void addSegment(T _from, T _to) {
        graph.addSegment(_from, _to);
    }

    public void addReversedSegment(ArrowedSegment<T> _seg) {
        graph.addReversedSegment(_seg);
    }

    public void addSegment(ArrowedSegment<T> _seg) {
        graph.addSegment(_seg);
    }

    public boolean isTrees() {
        return graph.isTrees();
    }

    public EqList<T> elementsCycle() {
        return graph.elementsCycle();
    }

    public CustList<ArrowedSegment<T>> getLines(T _root) {
        return graph.getLines(_root);
    }

    public boolean hasCycle() {
        return graph.hasCycle();
    }

    public EqList<T> getChildren(T _element) {
        return graph.getChildren(_element);
    }

    public CustList<ArrowedSegment<T>> getChildrenSegments(T _element) {
        return graph.getChildrenSegments(_element);
    }

    public EqList<T> getDynamicSeparations() {
        return graph.getDynamicSeparations();
    }

    public boolean hasPseudoRoots() {
        return graph.hasPseudoRoots();
    }

    public EqList<T> pseudoRoots() {
        return graph.pseudoRoots();
    }

    public boolean isDirectTrees() {
        return graph.isDirectTrees();
    }

    public Graph<T> getReverse() {
        return graph.getReverse();
    }

    public T getElementByEq(T _eq) {
        return graph.getElementByEq(_eq);
    }

    public EqList<T> getElementsListCopy() {
        return graph.getElementsListCopy();
    }

}
