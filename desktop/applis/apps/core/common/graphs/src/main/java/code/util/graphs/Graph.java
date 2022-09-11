package code.util.graphs;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class Graph {

    private final CustList<ArrowedSegment> segments = new CustList<ArrowedSegment>();
    private final CustList<SortedNumberedNode> elements = new CustList<SortedNumberedNode>();

    public static boolean containsNode(CustList<SortedNumberedNode> _list, SortedNumberedNode _node) {
        for (SortedNumberedNode n: _list) {
            if (_node.eq(n)) {
                return true;
            }
        }
        return false;
    }

    //    private final List<T> froms = new List<T>();

//    private final List<T> tos = new List<T>();

//    private final List<T> separations = new List<T>();

    public void addSegment(SortedNumberedNode _from, SortedNumberedNode _to) {
        addSegment(new ArrowedSegment(_from, _to));
    }
    public void addReversedSegment(ArrowedSegment _seg) {
        addSegment(new ArrowedSegment(_seg.getTo(), _seg.getFrom()));
    }

    public void addSegment(ArrowedSegment _seg) {
        segments.add(_seg);
        SortedNumberedNode e_ = _seg.getFrom();
        addIfPossible(elements, e_);
        e_ = _seg.getTo();
        addIfPossible(elements, e_);
    /*if (!addFrom_ && !addTo_) {
            boolean sep_ = false;
            for (T e: separations) {
                if (e.eq(e_)) {
                    sep_ = true;
                    break;
                }
            }
            if (!sep_) {
                separations.add(e_);
            }
        }*/
    }

    public boolean isTrees() {
        if (isDirectTrees()) {
            return true;
        }
        return getReverse().isDirectTrees();
    }
    public CustList<SortedNumberedNode> getTreeFrom(SortedNumberedNode _elt) {
        CustList<SortedNumberedNode> current_ = oneElement(_elt);
        CustList<SortedNumberedNode> visited_ = oneElement(_elt);
        CustList<SortedNumberedNode> new_ = new CustList<SortedNumberedNode>();
        while (true) {
            for (SortedNumberedNode t: current_) {
                for (ArrowedSegment u: getChildrenSegments(t)) {
                    tryAddVisited(visited_, new_, u.getTo());
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            current_ = new_;
            new_ = new CustList<SortedNumberedNode>();
        }
        return visited_;
    }

    private CustList<SortedNumberedNode> oneElement(SortedNumberedNode _elt) {
        CustList<SortedNumberedNode> current_ = new CustList<SortedNumberedNode>();
        current_.add(_elt);
        return current_;
    }

    public CustList<SortedNumberedNode> elementsCycle() {
//        if (segments.isEmpty()) {
//            return new List<T>();
//        }
        CustList<SortedNumberedNode> sep_ = getDynamicSeparations();
//        if (!hasPseudoRoots()) {
//            if (sep_.isEmpty()) {
//                return getElements();
//            }
//        }
        CustList<SortedNumberedNode> l_ = new CustList<SortedNumberedNode>();
//        if (!hasPseudoRoots()) {
//            int i_ = List.FIRST_INDEX;
//            if (sep_.isEmpty()) {
//                return getElements();
//            }
//            while (true) {
//                T s_ = sep_.get(i_);
//                List<Segment<T>> lines_ = new List<Segment<T>>();
//                List<T> current_ = new List<T>(s_);
//                List<T> visited_ = new List<T>(s_);
//                List<T> new_ = new List<T>();
//                boolean found_ = false;
//                while (true) {
//                    for (T t: current_) {
//                        for (Segment<T> u: getChildrenSegments(t)) {
//                            lines_.add(u);
//                            if (u.getTo().eq(s_)) {
//                                found_ = true;
//                                Graph<T> gLoc_ = new Graph<T>();
//                                for (Segment<T> l: lines_) {
//                                    gLoc_.addReversedSegment(l);
//                                }
//                                for (Segment<T> m: gLoc_.getLines(s_)) {
//                                    T to_ = m.getTo();
//                                    boolean add_ = true;
//                                    for (T e: l_) {
//                                        if (e.eq(to_)) {
//                                            add_ = false;
//                                            break;
//                                        }
//                                    }
//                                    if (!add_) {
//                                        continue;
//                                    }
//                                    l_.add(to_);
//                                }
//                                break;
//                            }
//                            boolean ex_ = false;
//                            for (T e: visited_) {
//                                if (e.eq(u.getTo())) {
//                                    //!e.eq(s_)
//                                    ex_ = true;
//                                    break;
//                                }
//                            }
//                            if (ex_) {
//                                continue;
//                            }
//                            new_.add(u.getTo());
//                            visited_.add(u.getTo());
//                        }
//                        if (found_) {
//                            break;
//                        }
//                    }
//                    if (found_) {
//                        break;
//                    }
//                    if (new_.isEmpty()) {
//                        break;
//                    }
//                    current_ = new_;
//                    new_ = new List<T>();
//                }
//                if (found_) {
//                    break;
//                }
//                i_++;
//            }
//            return l_;
//        }
        for (SortedNumberedNode s: sep_) {
            if (found(s,l_)) {
                break;
            }
        }
        return l_;
    }
    private boolean found(SortedNumberedNode _s, CustList<SortedNumberedNode> _l) {
        CustList<SortedNumberedNode> current_ = oneElement(_s);
        CustList<SortedNumberedNode> visited_ = oneElement(_s);
        boolean found_ = false;
        CustList<ArrowedSegment> lines_ = new CustList<ArrowedSegment>();
        while (true) {
            CustList<SortedNumberedNode> new_ = new CustList<SortedNumberedNode>();
            for (SortedNumberedNode t: current_) {
                for (ArrowedSegment u: getChildrenSegments(t)) {
                    lines_.add(u);
                    if (u.getTo().eq(_s)) {
                        addSegs(_l, _s, lines_);
                        found_ = true;
                        break;
                    }
                    tryAddVisited(visited_, new_, u.getTo());
                }
                if (found_) {
                    break;
                }
            }
            if (stop(new_, found_)) {
                break;
            }
            current_ = new_;
        }
        return found_;
    }

    private boolean stop(CustList<SortedNumberedNode> _new, boolean _found) {
        return _found || _new.isEmpty();
    }

    private void addSegs(CustList<SortedNumberedNode> _l, SortedNumberedNode _s, CustList<ArrowedSegment> _lines) {
        Graph gLoc_ = new Graph();
        for (ArrowedSegment l: _lines) {
            gLoc_.addReversedSegment(l);
        }
        for (ArrowedSegment m: gLoc_.getLines(_s)) {
            SortedNumberedNode to_ = m.getTo();
            addIfPossible(_l, to_);
        }
    }

    private void addIfPossible(CustList<SortedNumberedNode> _l, SortedNumberedNode _elt) {
        if (!containsNode(_l, _elt)) {
            _l.add(_elt);
        }
    }

    public CustList<ArrowedSegment> getLines(SortedNumberedNode _root) {
        CustList<SortedNumberedNode> current_ = oneElement(_root);
        CustList<SortedNumberedNode> visited_ = oneElement(_root);
        CustList<SortedNumberedNode> new_ = new CustList<SortedNumberedNode>();
        CustList<ArrowedSegment> lines_ = new CustList<ArrowedSegment>();
        while (true) {
            for (SortedNumberedNode t: current_) {
                addNewVisit(visited_, new_, lines_, t);
            }
            if (new_.isEmpty()) {
                break;
            }
            current_ = new_;
            new_ = new CustList<SortedNumberedNode>();
        }
        return lines_;
    }

    private void addNewVisit(CustList<SortedNumberedNode> _visited, CustList<SortedNumberedNode> _new, CustList<ArrowedSegment> _lines, SortedNumberedNode _t) {
        for (ArrowedSegment u: getChildrenSegments(_t)) {
            _lines.add(u);
            tryAddVisited(_visited, _new, u.getTo());
        }
    }

    //    public boolean quickHasCycle() {
//        if (segments.isEmpty()) {
//            return false;
//        }
//        if (!hasPseudoRoots()) {
//            return true;
//        }
//        for (T s: getSeparations()) {
//            List<T> current_ = new List<T>(s);
//            List<T> visited_ = new List<T>(s);
//            List<T> new_ = new List<T>();
//            while (true) {
//                for (T t: current_) {
//                    for (T u: getChildren(t)) {
//                        if (u.eq(s)) {
//                            return true;
//                        }
//                        boolean ex_ = false;
//                        for (T e: visited_) {
//                            if (e.eq(u)) {
//                                //!e.eq(s)
//                                ex_ = true;
//                                break;
//                            }
//                        }
//                        if (ex_) {
//                            continue;
//                        }
//                        new_.add(u);
//                        visited_.add(u);
//                    }
//                }
//                if (new_.isEmpty()) {
//                    break;
//                }
//                current_ = new_;
//                new_ = new List<T>();
//            }
//        }
//        return false;
//    }
//    public boolean quickHasCycle(T _t) {
//        List<Segment<T>> mystack_ = new List<Segment<T>>();
//        mystack_.add(new Segment<T>(_t, null));
//        List<Pair<T,Boolean>> visited_ = new List<Pair<T,Boolean>>();
//        for (T e: getElements()) {
//            visited_.add(new Pair<T,Boolean>(e, false));
//        }
//        for (Pair<T,Boolean> p: visited_) {
//            if (p.getFirst().eq(_t)) {
//                p.setSecond(true);
//                break;
//            }
//        }
////        visited[start] = true;
//
//        while (!mystack_.isEmpty())
//        {
//            T f_ = mystack_.last().getFrom();
//            T t_ = mystack_.last().getTo();
//            mystack_.removeLast();
//
////            const auto &edges = mygraph->edges[v];
//            List<T> edges_ = getChildren(f_);
//            for (T i: edges_) {
////                boolean v_ = false;
//                Pair<T,Boolean> p_ = null;
//                for (Pair<T,Boolean> p: visited_) {
//                    if (p.getFirst().eq(i)) {
//                        p_ = p;
////                        v_ = p.getSecond();
//                        break;
//                    }
//                }
//                if (!p_.getSecond()) {
//                    mystack_.add(new Segment<T>(i, f_));
////                    for (Pair<T,Boolean> p: visited_) {
////                        if (p.getFirst().eq(i)) {
////                            p.setSecond(true);
////                        }
////                    }
//                    p_.setSecond(true);
//                    continue;
////                    visited[w] = true;
//                }
//                if (!i.eq(t_)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    public boolean hasCycle() {
        if (segments.isEmpty()) {
            return false;
        }
        if (!hasPseudoRoots()) {
            return true;
        }
        return hasCycleDef();
    }

    private boolean hasCycleDef() {
        for (SortedNumberedNode s: getDynamicSeparations()) {
            if (hasCycleOne(s)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleOne(SortedNumberedNode _s) {
        CustList<SortedNumberedNode> current_ = oneElement(_s);
        CustList<SortedNumberedNode> visited_ = oneElement(_s);
        while (true) {
            CustList<SortedNumberedNode> new_ = new CustList<SortedNumberedNode>();
            for (SortedNumberedNode t: current_) {
                for (SortedNumberedNode u: getChildren(t)) {
                    if (u.eq(_s)) {
                        return true;
                    }
                    tryAddVisited(visited_, new_, u);
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            current_ = new_;
        }
        return false;
    }

    private void tryAddVisited(CustList<SortedNumberedNode> _visited, CustList<SortedNumberedNode> _new, SortedNumberedNode _node) {
        boolean ex_ = containsNode(_visited, _node);
        if (ex_) {
            return;
        }
        _new.add(_node);
        _visited.add(_node);
    }

    public CustList<SortedNumberedNode> getChildren(SortedNumberedNode _element) {
        CustList<SortedNumberedNode> c_ = new CustList<SortedNumberedNode>();
        for (ArrowedSegment s: getChildrenSegments(_element)) {
            c_.add(s.getTo());
        }
        return c_;
    }
    public CustList<ArrowedSegment> getChildrenSegments(SortedNumberedNode _element) {
        CustList<ArrowedSegment> c_ = new CustList<ArrowedSegment>();
        for (ArrowedSegment s: segments) {
            if (s.getFrom().eq(_element)) {
                c_.add(s);
            }
        }
        return c_;
    }
//    public List<T> getSeparations() {
//        return separations;
////        List<T> r_ = new List<T>();
////        List<T> elts_ = getElements();
////        for (T e: elts_) {
////            int nbOne_ = List.SIZE_EMPTY;
////            for (Segment<T> s: segments) {
////                if (s.getTo().eq(e)) {
////                    nbOne_++;
////                }
////            }
////            int nbTwo_ = List.SIZE_EMPTY;
////            for (Segment<T> s: segments) {
////                if (s.getFrom().eq(e)) {
////                    nbTwo_++;
////                }
////            }
//////            if (nb_ >= List.ONE_ELEMENT) {
//////                r_.add(e);
//////                continue;
//////            }
//////            if (nbTwo_ < List.ONE_ELEMENT) {
//////                continue;
//////            }
//////            if (nbOne_ <= List.ONE_ELEMENT) {
//////                continue;
//////            }
////            if (nbOne_ <= List.ONE_ELEMENT) {
////                if (nbTwo_ <= List.ONE_ELEMENT) {
////                    continue;
////                }
////            }
////            r_.add(e);
//////            if (nbOne_ == List.ONE_ELEMENT) {
//////                if (nbTwo_ > List.ONE_ELEMENT) {
//////                    r_.add(e);
//////                }
//////            }
//////            if (nbOne_ > List.ONE_ELEMENT) {
//////                if (nbTwo_ >= List.ONE_ELEMENT) {
//////                    r_.add(e);
//////                }
//////            }
//////            if (nbOne_ < List.ONE_ELEMENT) {
//////                continue;
//////            }
////////////            if (nbTwo_ > List.ONE_ELEMENT) {
//////                r_.add(e);
//////            }
////        }
////        return r_;
//    }
    public CustList<SortedNumberedNode> getDynamicSeparations() {
        CustList<SortedNumberedNode> r_ = new CustList<SortedNumberedNode>();
        CustList<SortedNumberedNode> elts_ = getElements();
        for (SortedNumberedNode e: elts_) {
            if (notFullBound(e)) {
                continue;
            }
            //            if (nbOne_ <= List.ONE_ELEMENT) {
//                if (nbTwo_ <= List.ONE_ELEMENT) {
//                    continue;
//                }
//            }
            r_.add(e);
        }
        return r_;
    }

    private boolean notFullBound(SortedNumberedNode _e) {
        return nbTo(_e) == IndexConstants.SIZE_EMPTY || nbFrom(_e) == IndexConstants.SIZE_EMPTY;
    }

    public boolean hasPseudoRoots() {
        CustList<SortedNumberedNode> elts_ = getElements();
        for (SortedNumberedNode e: elts_) {
            if (notFullBound(e)) {
                return true;
            }
        }
        return false;
    }

    private int nbFrom(SortedNumberedNode _e) {
        int nb_ = IndexConstants.SIZE_EMPTY;
        for (ArrowedSegment s: segments) {
            if (s.getFrom().eq(_e)) {
                nb_++;
            }
        }
        return nb_;
    }

    private int nbTo(SortedNumberedNode _e) {
        int nb_ = IndexConstants.SIZE_EMPTY;
        for (ArrowedSegment s: segments) {
            if (s.getTo().eq(_e)) {
                nb_++;
            }
        }
        return nb_;
    }

    public CustList<SortedNumberedNode> pseudoRoots() {
        CustList<SortedNumberedNode> r_ = new CustList<SortedNumberedNode>();
        CustList<SortedNumberedNode> elts_ = getElements();
        for (SortedNumberedNode e: elts_) {
            if (notFullBound(e)) {
                r_.add(e);
            }
        }
        return r_;
    }

    public boolean isDirectTrees() {
        if (segments.isEmpty()) {
            return true;
        }
        if (!hasPseudoRoots()) {
            return false;
        }
        CustList<SortedNumberedNode> elts_ = getElements();
//        boolean exist_ = false;
        for (SortedNumberedNode e: elts_) {
            int nb_ = nbTo(e);
//            if (nb_ == List.SIZE_EMPTY) {
//                exist_ = true;
//            }
//            if (nb_ == List.ONE_ELEMENT) {
//                continue;
//            }
//            if (nb_ > List.ONE_ELEMENT || !exist_)
            if (nb_ > IndexConstants.ONE_ELEMENT) {
                return false;
            }
        }
        return true;
    }

    public Graph getReverse() {
        Graph r_ = new Graph();
        for (ArrowedSegment s: segments) {
            r_.addReversedSegment(s);
        }
        return r_;
    }

    public CustList<SortedNumberedNode> getElementsListCopy() {
        return new CustList<SortedNumberedNode>(elements);
    }

    CustList<SortedNumberedNode> getElements() {
        return elements;
//        List<T> l_ = new List<T>();
//        for (Segment<T> s: segments) {
//            boolean addFrom_ = true;
//            boolean addTo_ = true;
//            for (T e: l_) {
//                if (e.eq(s.getFrom())) {
//                    addFrom_ = false;
//                }
//                if (e.eq(s.getTo())) {
//                    addTo_ = false;
//                }
//            }
//            if (addFrom_) {
//                l_.add(s.getFrom());
//            }
//            if (addTo_) {
//                l_.add(s.getTo());
//            }
//        }
//        return l_;
    }
}
