package code.util.graphs;

import code.util.CustList;
import code.util.EqList;
import code.util.core.IndexConstants;
import code.util.ints.SortedEdge;

public final class SortedGraph<T extends SortedEdge<T>> {

    private final Graph<T> graph = new Graph<T>();

    public EqList<T> process() {
        EqList<T> elts_ = graph.getElements();
        EqList<T> result_ = new EqList<T>();
        int order_ = 0;
        while (true) {
            EqList<T> next_ = new EqList<T>();
            for (T e: elts_) {
                if (e.getOrder() > IndexConstants.INDEX_NOT_FOUND_ELT) {
                    continue;
                }
                if (allNumbered(graph.getChildren(e))) {
                    next_.add(e);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            for (T o: next_) {
                o.setOrder(order_);
                result_.add(o);
                order_++;
            }
        }
        return result_;
    }

    private boolean allNumbered(EqList<T> _list) {
        boolean allNb_ = true;
        for (T s: _list) {
            if (s.getOrder() == IndexConstants.INDEX_NOT_FOUND_ELT) {
                allNb_ = false;
                break;
            }
        }
        return allNb_;
    }

    public EqList<T> getTreeFrom(T _elt) {
        return graph.getTreeFrom(_elt);
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

    public EqList<T> getElementsListCopy() {
        return graph.getElementsListCopy();
    }

}
