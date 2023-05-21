package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class LocationsTreeEvent implements AbsShortListTree {
    private final AnalyzedPageEl page;
    private final WindowWithTreeImpl window;
    private final MetaCaller root;

    public LocationsTreeEvent(AnalyzedPageEl _p,WindowWithTreeImpl _w,MetaCaller _r) {
        this.page = _p;
        this.window = _w;
        this.root = _r;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _node) {
        AbstractMutableTreeNodeCore e_ = MutableTreeNodeUtil.simular(root, (AbstractMutableTreeNode) _node);
        if (!(e_ instanceof MetaCaller)) {
            return;
        }
        CustList<FileBlockIndex> locs_ = ((MetaCaller)e_).getNumber();
        int len_ = locs_.size();
        AbsCompoFactory comp_ = window.getCommonFrame().getFrames().getCompoFactory();
        AbsPanel pa_ = comp_.newPageBox();
        for (int i = 0; i < len_; i++) {
            FileBlockIndex elt_ = locs_.get(i);
            String display_ = "" + elt_.getIndex();
            RowSrcLocation r_ = new RowSrcLocation(null, display_, FileBlock.name(elt_.getFile()), elt_.getIndex());
            AbsPlainButton button_ = comp_.newPlainButton(display_);
            button_.addActionListener(new GoToDefinitionEvent(page,r_,window));
            pa_.add(button_);
        }
        window.getPanelSymbolsLocationScroll().setViewportView(pa_);
        GuiBaseUtil.recalculate(window.getDetail());
    }
}
