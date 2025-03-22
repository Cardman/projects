package cards.gui.dialogs.events;

import cards.gui.dialogs.BeanBuilderHelperCards;
import cards.gui.dialogs.FindBeanCardsEvent;
import cards.gui.dialogs.FrameGeneralHelp;
import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.NodeHelp;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public class ListenerClickTree implements AbsShortListTree {

    private final AbstractProgramInfos frames;
    private final FrameGeneralHelp frame;
    private final NodeHelp node;

    private final AbsScrollPane editor;

    private final AbsTreeGui tree;

    public ListenerClickTree(NodeHelp _node,
                             AbsScrollPane _editor, AbsTreeGui _tree, FrameGeneralHelp _fr, AbstractProgramInfos _frs) {
        node = _node;
        editor = _editor;
        tree = _tree;
        frame = _fr;
        frames = _frs;
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
//        PreparedRenderPagesCards prep_ = new PreparedRenderPagesCards(editor.getGene().currentLg().getMapping().getVal(MessagesHelpCards.APP_BEAN), element_.ct().getVal(concat_), editor.getGene().currentLg().getMaxiCards(), element_.built().getVal(concat_));
//        prep_.run();
//        FrameGeneralHelp.initialize(prep_.getMetaDocument(), editor, prep_.getNavigation());
        GuiBaseUtil.removeActionListeners(frame.getSearch());
        BeanBuilderHelperCards cards_ = new BeanBuilderHelperCards(frames);
        cards_.setScrollPane(editor);
        cards_.setFrame(frame.getCommonFrame());
        FindBeanCardsEvent findEv_ = new FindBeanCardsEvent(frame.getField(), frames);
        frame.getSearch().addActionListener(findEv_);
        cards_.initPage();
        element_.built().getVal(concat_).format(cards_,frames.currentLg());
//        racineBis.getElementLocal().built().getVal(concat_).format(cards_,frames.currentLg());
        findEv_.setFinding(cards_);
        editor.setViewportView(cards_.getStackCards().last());
    }
}
