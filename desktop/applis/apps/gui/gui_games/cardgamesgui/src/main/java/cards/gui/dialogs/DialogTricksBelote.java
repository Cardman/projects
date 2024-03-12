package cards.gui.dialogs;




import cards.belote.DisplayingBelote;
import cards.belote.RulesBelote;
import cards.belote.TricksHandsBelote;
import cards.gui.WindowCardsInt;
import cards.gui.panels.PanelTricksHandsBelote;
import code.gui.*;

import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;

public final class DialogTricksBelote extends DialogHelpCards {

    private PanelTricksHandsBelote panelTricksHandsBelote;
    public DialogTricksBelote(AbstractProgramInfos _frameFactory, AbstractAtomicBoolean _modal) {
        super(_frameFactory,_modal);
    }
    public static void setDialogTricksBelote(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
//        _fenetre.getDialogTricksBelote().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
//        _fenetre.getDialogTricksBelote().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogTricksBelote().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogTricksBelote().setTitleDialog(_fenetre,_titre);
//        _fenetre.getDialogTricksBelote().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsBelote _tricksHands,
                            RulesBelote _rules, StringList _pseudos,
                            DisplayingBelote _displayingBelote, WindowCardsInt _ow) {
        _ow.getDialogTricksBelote().setDialogue(_tricksHands, _rules,_pseudos, _displayingBelote, _ow);
    }

    private void setDialogue(TricksHandsBelote _tricksHands,
                             RulesBelote _rules, StringList _pseudos,
                             DisplayingBelote _displayingBelote, WindowCardsInt _ow) {
        _tricksHands.sortHands(_displayingBelote,_rules);
        panelTricksHandsBelote = new PanelTricksHandsBelote(getAbsDialog(),
                _tricksHands, _rules, _pseudos, _displayingBelote, _ow);
        AbsPanel scroll_ = panelTricksHandsBelote.getContainer();
        scroll_.setPreferredSize(new MetaDimension(850, 850));
        getAbsDialog().setContentPane(scroll_);
        getAbsDialog().pack();
        getAbsDialog().setVisible(true);
    }

    public PanelTricksHandsBelote getPanelTricksHandsBelote() {
        return panelTricksHandsBelote;
    }
}
