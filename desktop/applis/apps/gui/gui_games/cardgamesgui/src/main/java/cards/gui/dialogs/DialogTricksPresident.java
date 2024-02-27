package cards.gui.dialogs;




import cards.gui.WindowCardsInt;
import cards.gui.panels.PanelTricksHandsPresident;
import cards.president.DisplayingPresident;
import cards.president.TricksHandsPresident;
import code.gui.*;

import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class DialogTricksPresident extends DialogCards {

    private PanelTricksHandsPresident panelTricksHandsPresident;

    public DialogTricksPresident(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
    }
    public static void setDialogTricksPresident(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogTricksPresident().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogTricksPresident().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogTricksPresident().getCardDialog().setTitle(_titre);
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
        panelTricksHandsPresident = new PanelTricksHandsPresident(getCardDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingPresident, _window);
        AbsScrollPane scroll_ = _window.getCompoFactory().newAbsScrollPane(panelTricksHandsPresident.getContainer());
        scroll_.setPreferredSize(new MetaDimension(600, 600));
        getCardDialog().setContentPane(scroll_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }

    public PanelTricksHandsPresident getPanelTricksHandsPresident() {
        return panelTricksHandsPresident;
    }
}
