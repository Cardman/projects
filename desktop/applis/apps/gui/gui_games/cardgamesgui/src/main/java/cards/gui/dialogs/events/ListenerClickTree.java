package cards.gui.dialogs.events;

import cards.gui.animations.PreparedRenderPagesCards;
import cards.gui.dialogs.FrameGeneralHelp;
import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.NodeHelp;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.scripts.pages.cards.HelpCards;
import code.util.*;

public class ListenerClickTree implements AbsShortListTree {

    private final NodeHelp node;

    private final RenderedPage editor;

    private final AbsTreeGui tree;

    public ListenerClickTree(NodeHelp _node,
            RenderedPage _editor, AbsTreeGui _tree) {
        node = _node;
        editor = _editor;
        tree = _tree;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _e) {
        AbstractMutableTreeNodeCore<String> sel_ = tree.selectEvt();
        if (sel_ == null) {
            return;
        }
        Ints indices_ = sel_.getIndexes();
        ElementHelp element_ = node.element(indices_).getElementLocal();
        String concat_ = element_.chemin();
//        StringMap<TranslationsAppli> builtMs_ = HelpCards.ms();
//        NavigationCore.adjustMap(builtMs_);
        PreparedRenderPagesCards prep_ = new PreparedRenderPagesCards(editor.getGene().currentLg().getMapping().getVal(HelpCards.APP_BEAN), element_.ct().getVal(concat_), editor.getGene().currentLg().getMaxiCards(), element_.built().getVal(concat_));
        prep_.run();
        FrameGeneralHelp.initialize(prep_.getMetaDocument(), editor, prep_.getNavigation());
    }
}
