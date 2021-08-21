package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import cards.gui.WindowCards;
import cards.gui.panels.PanelTricksHandsPresident;
import cards.president.DisplayingPresident;
import cards.president.TricksHandsPresident;
import code.gui.ScrollPane;
import code.gui.initialize.AbsFrameFactory;
import code.util.StringList;

public final class DialogTricksPresident extends DialogCards {

    public DialogTricksPresident(AbsFrameFactory _frameFactory) {
        super(_frameFactory);
    }
    public static void setDialogTricksPresident(String _titre, WindowCards _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogTricksPresident().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogTricksPresident().getCardDialog().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogTricksPresident().getCardDialog().setTitle(_titre);
        _fenetre.getDialogTricksPresident().getCardDialog().setResizable(true);
        _fenetre.getDialogTricksPresident().getCardDialog().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, WindowCards _window) {
        _window.getDialogTricksPresident().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingPresident,_window);
    }

    private void setDialogue(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, WindowCards _window) {
        _tricksHands.sortHands(_displayingPresident, _numberPlayers);
        ScrollPane scroll_ = new ScrollPane(new PanelTricksHandsPresident(getCardDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingPresident,_window).getContainer());
        scroll_.setPreferredSize(new Dimension(600, 600));
        getCardDialog().setContentPane(scroll_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }
}
