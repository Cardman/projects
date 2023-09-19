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

}
