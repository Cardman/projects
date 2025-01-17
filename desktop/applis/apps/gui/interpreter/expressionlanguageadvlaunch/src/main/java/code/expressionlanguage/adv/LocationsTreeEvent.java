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
    public void valueChanged(AbstractMutableTreeNodeCore<String> _node) {
        AbstractMutableTreeNodeCore<MetaCaller> f_ = root.getMeta().simular(_node);
        if (f_ == null) {
            return;
        }
        MetaCaller e_ = f_.info();
        CustList<FileBlockIndex> locs_ = e_.getNumber();
        int len_ = locs_.size();
        AbsCompoFactory comp_ = window.getFrames().getCompoFactory();
        AbsPanel pa_ = comp_.newPageBox();
        for (int i = 0; i < len_; i++) {
            FileBlockIndex elt_ = locs_.get(i);
            String name_ = FileBlock.name(elt_.getFile());
            int locIndex_ = elt_.getIndex();
            String dis_ = TreeNodeRenderUtil.locations(elt_);
            if (dis_.isEmpty()) {
                continue;
            }
            RowSrcLocation r_ = new RowSrcLocation(null, dis_, name_, locIndex_);
            AbsButton button_ = comp_.newPlainButton(dis_);
            button_.disabledRichText(false);
            button_.addActionListener(new GoToDefinitionEvent(page,r_,window));
            pa_.add(button_);
        }
        window.getPanelSymbolsLocationScroll().setViewportView(pa_);
        window.pack();
//        GuiBaseUtil.recalculate(window.getPanelSymbolsLocationScroll());
    }

}
