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
        }
        return rankedList_;
    }
}
