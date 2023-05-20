package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class LocationsTreeEvent implements AbsShortListTree {
    private final WindowWithTreeImpl window;
    private final MetaCaller root;

    public LocationsTreeEvent(WindowWithTreeImpl _w,MetaCaller _r) {
        this.window = _w;
        this.root = _r;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _node) {
        AbstractMutableTreeNodeCore e_ = MutableTreeNodeCoreUtil.getElt(root, MutableTreeNodeUtil.getIndexes((AbstractMutableTreeNode) _node));
        if (!(e_ instanceof MetaCaller)) {
            return;
        }
        CustList<FileBlockIndex> locs_ = ((MetaCaller)e_).getNumber();
        int len_ = locs_.size();
        AbsCompoFactory comp_ = window.getCommonFrame().getFrames().getCompoFactory();
        AbsPanel pa_ = comp_.newPageBox();
        for (int i = 0; i < len_; i++) {
            FileBlockIndex elt_ = locs_.get(i);
            pa_.add(comp_.newPlainLabel(""+elt_.getIndex()));
        }
        window.getPanelSymbolsLocationScroll().setViewportView(pa_);
        GuiBaseUtil.recalculate(window.getDetail());
    }
}
