package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import cards.gui.MainWindow;
import cards.gui.panels.PanelTricksHandsPresident;
import cards.president.DisplayingPresident;
import cards.president.TricksHandsPresident;
import code.gui.ScrollPane;
import code.util.StringList;

public final class DialogTricksPresident extends DialogCards {

    public static void setDialogTricksPresident(String _titre, MainWindow _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogTricksPresident().setDialogIcon(_fenetre);
        _fenetre.getDialogTricksPresident().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogTricksPresident().setTitle(_titre);
        _fenetre.getDialogTricksPresident().setResizable(true);
        _fenetre.getDialogTricksPresident().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, MainWindow _window) {
        _window.getDialogTricksPresident().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingPresident,_window);
    }

    private void setDialogue(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, MainWindow _window) {
        _tricksHands.sortHands(_displayingPresident, _numberPlayers);
        ScrollPane scroll_ = new ScrollPane(new PanelTricksHandsPresident(this,
                _tricksHands, _numberPlayers, _pseudos, _displayingPresident,_window).getContainer());
        scroll_.setPreferredSize(new Dimension(600, 600));
        setContentPane(scroll_);
        pack();
        setVisible(true);
    }
}
