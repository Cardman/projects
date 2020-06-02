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

    private static final DialogTricksPresident DIALOG = new DialogTricksPresident();

    private DialogTricksPresident() {
    }

    public static void setDialogTricksPresident(String _titre, MainWindow _fenetre) {
        //super(_titre, _fenetre, true);
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.setTitle(_titre);
        DIALOG.setResizable(true);
        DIALOG.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, MainWindow _window) {
        DIALOG.setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingPresident,_window);
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
