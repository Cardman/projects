package cards.gui.dialogs;




import cards.gui.WindowCardsInt;
import cards.gui.panels.PanelTricksHandsPresident;
import cards.president.DisplayingPresident;
import cards.president.TricksHandsPresident;
import code.gui.*;

import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;

public final class DialogTricksPresident extends DialogHelpCards {

    private PanelTricksHandsPresident panelTricksHandsPresident;

    public DialogTricksPresident(AbstractProgramInfos _frameFactory, AbstractAtomicBoolean _modal) {
        super(_frameFactory,_modal);
    }
    public static void setDialogTricksPresident(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
//        _fenetre.getDialogTricksPresident().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
//        _fenetre.getDialogTricksPresident().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogTricksPresident().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogTricksPresident().setTitleDialog(_fenetre,_titre);
//        _fenetre.getDialogTricksPresident().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, WindowCardsInt _window) {
        _window.getDialogTricksPresident().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingPresident,_window);
    }

    private void setDialogue(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, WindowCardsInt _window) {
        _tricksHands.sortHands(_displayingPresident, _numberPlayers);
        panelTricksHandsPresident = new PanelTricksHandsPresident(getAbsDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingPresident, _window);
        AbsPanel scroll_ = panelTricksHandsPresident.getContainer();
        scroll_.setPreferredSize(new MetaDimension(850, 850));
        getAbsDialog().setContentPane(scroll_);
        getAbsDialog().pack();
        getAbsDialog().setVisible(true);
    }

    public PanelTricksHandsPresident getPanelTricksHandsPresident() {
        return panelTricksHandsPresident;
    }
}
