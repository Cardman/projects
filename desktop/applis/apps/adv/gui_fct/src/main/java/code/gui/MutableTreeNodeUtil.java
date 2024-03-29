package code.gui;

import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class MutableTreeNodeUtil {
    private MutableTreeNodeUtil() {
    }
    public static void reload(AbsTreeGui _abs) {
        AbstractMutableTreeNodeCore<String> sel_ = _abs.selectEvt();
        if (sel_ != null) {
            _abs.reload(sel_);
        } else {
            _abs.reloadRoot();
        }
    }
    public static CustList<AbstractMutableTreeNodeCore<String>> list(AbstractMutableTreeNodeCore<String> _root,AbsTreePaths _paths) {
        int len_ = _paths.getLength();
        CustList<AbstractMutableTreeNodeCore<String>> ls_ = new CustList<AbstractMutableTreeNodeCore<String>>();
        for (int i = 0; i < len_; i++) {
            ls_.add(_paths.elt(_root,i).data());
        }
        return ls_;
    }
    public static AbsTreePaths list(AbsCompoFactory _compo, AbsTreeGui _root, CustList<AbstractMutableTreeNodeCore<String>> _paths) {
        int len_ = _paths.size();
        AbsTreePaths ls_ = _compo.emptyList();
        for (int i = 0; i < len_; i++) {
            ls_.add(_root.translate(_paths.get(i)));
        }
        return ls_;
    }
    public static AbstractMutableTreeNodeCore<String> parent(AbsTreeGui _tr) {
        AbsTreePath path_ = _tr.selectedPath();
        while (path_ != null) {
            AbstractMutableTreeNodeCore<String> d_ = path_.data();
            if (d_.getParent() != null) {
                return d_;
            }
            path_ = path_.parent(_tr);
        }
        return _tr.getRoot();
    }

    public static CustList<AbstractMutableTreeNodeCore<String>> listPaths(AbsTreeGui _tr,AbsTreePath _path) {
        int len_ = _path.getLength();
        CustList<AbstractMutableTreeNodeCore<String>> ls_ = new CustList<AbstractMutableTreeNodeCore<String>>();
        AbsTreePath par_ = _path;
        for (int i = 0; i < len_; i++) {
            ls_.add(0,par_.data());
            par_ = par_.parent(_tr);
        }
        return ls_;
    }
}
