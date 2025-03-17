package cards.gui.dialogs;


import cards.gui.WindowCardsInt;
import cards.gui.panels.PanelTricksHandsTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.TricksHandsTarot;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;

public final class DialogTricksTarot extends DialogHelpCards {

    private PanelTricksHandsTarot panelTricksHandsTarot;

    public DialogTricksTarot(AbstractProgramInfos _frameFactory, AbstractAtomicBoolean _modal) {
        super(_frameFactory,_modal);
    }
    public static void setDialogTricksTarot(String _titre, WindowCardsInt _fenetre) {
//        _fenetre.getDialogTricksTarot().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
//        _fenetre.getDialogTricksTarot().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogTricksTarot().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogTricksTarot().setTitleDialog(_fenetre,_titre);
//        _fenetre.getDialogTricksTarot().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }
    public static void init(TricksHandsTarot _tricksHands, int _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot, WindowCardsInt _window) {
        _window.getDialogTricksTarot().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingTarot,_window);
    }
    private void setDialogue(TricksHandsTarot _tricksHands, int _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot, WindowCardsInt _window) {

        _tricksHands.sortHands(_displayingTarot, _numberPlayers);
        panelTricksHandsTarot = new PanelTricksHandsTarot(getAbsDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingTarot, _window);
        AbsPanel scroll_ = panelTricksHandsTarot.getContainer();
        scroll_.setPreferredSize(new MetaDimension(850, 850));
        getAbsDialog().setContentPane(scroll_);
        getAbsDialog().pack();
        getAbsDialog().setVisible(true);
    }

    public PanelTricksHandsTarot getPanelTricksHandsTarot() {
        return panelTricksHandsTarot;
    }
}
