package code.util.opers;
import code.util.AbEqList;
import code.util.CustList;
import code.util.EqList;
import code.util.ints.Equallable;
import code.util.ints.Parent;
import code.util.ints.TreeFilter;

public final class TreeRetrieving {

    private TreeRetrieving() {
    }

    /**@return sorted list: parent, child, grand child, ...*/
    public static <U extends Parent<U>&TreeFilter<U>&Equallable<U>> AbEqList<U> getRankedList(U _root, AbEqList<U> _list) {
        AbEqList<U> allNodes_ = new EqList<U>();
        allNodes_.add(_root);
        CustList<U> currentNodes_ = new CustList<U>();
        currentNodes_.add(_root);
        CustList<U> newNodes_;
        while (true) {
            newNodes_ = new CustList<U>();
            for (U currentNode_: currentNodes_) {
                for (U child_: currentNode_.getChildren(allNodes_,_list)) {
                    newNodes_.add(child_);
                    allNodes_.add(child_);
                }
            }
            if (newNodes_.isEmpty()) {
                break;
            }
            currentNodes_ = new CustList<U>(newNodes_);
        }
        AbEqList<U> rankedList_ = new EqList<U>();
        for (U node_: allNodes_) {
            if (node_ == null) {
                continue;
            }
            boolean bool_ = node_.accept(_list);
            if (!bool_) {
                continue;
            }
            rankedList_.add(node_);
//            if (node_ instanceof TreeFilter) {
//////                try {
////                    boolean bool_ = ((TreeFilter)node_).accept(_list);
////                    if (!bool_) {
////                        continue;
////                    }
////                    rankedList_.add(node_);
////                } catch (IllegalArgumentException e) {
////                    throw new RuntimeException(e.getMessage());
////                }
//            }
        }
        return rankedList_;
    }

//    public static <T extends SortedNode<T>> CustList<T> getDirectChildren(T _element) {
//        CustList<T> list_ = new CustList<T>();
//        if (_element == null) {
//            return list_;
//        }
//        T firstChild_ = _element.getFirstChild();
//        T elt_ = firstChild_;
//        while (elt_ != null) {
//            list_.add(elt_);
//            elt_ = elt_.getNextSibling();
//        }
//        return list_;
//    }
//
//    public static <T extends SortedNode<T>> CustList<T> getSortedDescNodes(T _root) {
//        CustList<T> list_ = new CustList<T>();
//        if (_root == null) {
//            return list_;
//        }
//        T c_ = _root;
//        while (true) {
//            if (c_ == null) {
//                break;
//            }
//            list_.add(c_);
//            c_ = TreeRetrieving.<T>getNext(c_, _root);
////            list_.add(c_);
////            SortedNode n_ = c_.getFirstChild();
////            if (n_ != null) {
////                c_ = n_;
////                continue;
////            }
////            n_ = c_.getNextSibling();
////            if (n_ != null) {
////                c_ = n_;
////                continue;
////            }
////            n_ = c_.getParent();
////            if (n_ != null) {
////                SortedNode next_ = n_.getNextSibling();
////                while (next_ == null) {
////                    SortedNode par_ = n_.getParent();
////                    if (par_ == null) {
////                        break;
////                    }
////                    next_ = par_.getNextSibling();
////                    n_ = par_;
////                }
////                if (next_ != null) {
////                    c_ = next_;
////                } else {
////                    break;
////                }
////            } else {
////                break;
////            }
//        }
//        return list_;
//    }
//
//    public static <T extends SortedNode<T>> T getNext(T _current, T _root) {
//        T n_ = _current.getFirstChild();
//        if (n_ != null) {
//            return n_;
//        }
//        n_ = _current.getNextSibling();
//        if (n_ != null) {
//            return n_;
//        }
//        n_ = _current.getParent();
//        if (n_ == _root) {
//            return null;
//        }
//        if (n_ != null) {
//            T next_ = n_.getNextSibling();
//            while (next_ == null) {
//                T par_ = n_.getParent();
//                if (par_ == _root) {
//                    break;
//                }
//                if (par_ == null) {
//                    break;
//                }
//                next_ = par_.getNextSibling();
//                n_ = par_;
//            }
//            if (next_ != null) {
//                return next_;
//            }
//        }
//        return null;
//    }
}
