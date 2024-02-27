package cards.gui.dialogs;


import cards.gui.WindowCardsInt;
import cards.gui.panels.PanelTricksHandsTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.TricksHandsTarot;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class DialogTricksTarot extends DialogCards {

    private PanelTricksHandsTarot panelTricksHandsTarot;

    public DialogTricksTarot(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
    }
    public static void setDialogTricksTarot(String _titre, WindowCardsInt _fenetre) {
        _fenetre.getDialogTricksTarot().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogTricksTarot().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogTricksTarot().getCardDialog().setTitle(_titre);
//        _fenetre.getDialogTricksTarot().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }
    public static void init(TricksHandsTarot _tricksHands, byte _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot, WindowCardsInt _window) {
        _window.getDialogTricksTarot().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingTarot,_window);
    }
    private void setDialogue(TricksHandsTarot _tricksHands, byte _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot, WindowCardsInt _window) {

        _tricksHands.sortHands(_displayingTarot, _numberPlayers);
        panelTricksHandsTarot = new PanelTricksHandsTarot(getCardDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingTarot, _window);
        AbsScrollPane scroll_ = _window.getCompoFactory().newAbsScrollPane(panelTricksHandsTarot.getContainer());
        scroll_.setPreferredSize(new MetaDimension(600, 600));
        getCardDialog().setContentPane(scroll_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }

    public PanelTricksHandsTarot getPanelTricksHandsTarot() {
        return panelTricksHandsTarot;
    }
}
