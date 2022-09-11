package code.util.graphs;

import code.util.CustList;
import code.util.core.IndexConstants;

public final class SortedGraph {

    private final Graph graph = new Graph();

    public CustList<SortedNumberedNode> process() {
        CustList<SortedNumberedNode> elts_ = graph.getElements();
        CustList<SortedNumberedNode> result_ = new CustList<SortedNumberedNode>();
        int order_ = 0;
        while (true) {
            CustList<SortedNumberedNode> next_ = new CustList<SortedNumberedNode>();
            for (SortedNumberedNode e: elts_) {
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
            for (SortedNumberedNode o: next_) {
                o.setOrder(order_);
                result_.add(o);
                order_++;
            }
        }
        return result_;
    }

    private boolean allNumbered(CustList<SortedNumberedNode> _list) {
        boolean allNb_ = true;
        for (SortedNumberedNode s: _list) {
            if (s.getOrder() == IndexConstants.INDEX_NOT_FOUND_ELT) {
                allNb_ = false;
                break;
            }
        }
        return allNb_;
    }

    public CustList<SortedNumberedNode> getTreeFrom(SortedNumberedNode _elt) {
        return graph.getTreeFrom(_elt);
    }

    public void addSegment(SortedNumberedNode _from, SortedNumberedNode _to) {
        graph.addSegment(_from, _to);
    }

    public void addReversedSegment(ArrowedSegment _seg) {
        graph.addReversedSegment(_seg);
    }

    public void addSegment(ArrowedSegment _seg) {
        graph.addSegment(_seg);
    }

    public boolean isTrees() {
        return graph.isTrees();
    }

    public CustList<SortedNumberedNode> elementsCycle() {
        return graph.elementsCycle();
    }

    public CustList<ArrowedSegment> getLines(SortedNumberedNode _root) {
        return graph.getLines(_root);
    }

    public boolean hasCycle() {
        return graph.hasCycle();
    }

    public CustList<SortedNumberedNode> getChildren(SortedNumberedNode _element) {
        return graph.getChildren(_element);
    }

    public CustList<ArrowedSegment> getChildrenSegments(SortedNumberedNode _element) {
        return graph.getChildrenSegments(_element);
    }

    public CustList<SortedNumberedNode> getDynamicSeparations() {
        return graph.getDynamicSeparations();
    }

    public boolean hasPseudoRoots() {
        return graph.hasPseudoRoots();
    }

    public CustList<SortedNumberedNode> pseudoRoots() {
        return graph.pseudoRoots();
    }

    public boolean isDirectTrees() {
        return graph.isDirectTrees();
    }


    public Graph getReverse() {
        return graph.getReverse();
    }

    public CustList<SortedNumberedNode> getElementsListCopy() {
        return graph.getElementsListCopy();
    }

}
