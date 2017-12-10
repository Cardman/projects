package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import cards.gui.MainWindow;
import cards.gui.panels.PanelTricksHandsTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.TricksHandsTarot;
import code.util.StringList;

public final class DialogTricksTarot extends DialogCards {

    private static final DialogTricksTarot DIALOG = new DialogTricksTarot();

    private DialogTricksTarot() {
    }

    public static void setDialogTricksTarot(String _titre, MainWindow _fenetre) {
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.setTitle(_titre);
        DIALOG.setResizable(true);
        DIALOG.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
    public static void init(TricksHandsTarot _tricksHands, byte _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot) {
        DIALOG.setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingTarot);
    }
    private void setDialogue(TricksHandsTarot _tricksHands, byte _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot) {

        _tricksHands.sortHands(_displayingTarot, _numberPlayers);
        JScrollPane scroll_ = new JScrollPane(new PanelTricksHandsTarot(this,
                _tricksHands, _numberPlayers, _pseudos, _displayingTarot));
        scroll_.setPreferredSize(new Dimension(600, 600));
        setContentPane(scroll_);
        pack();
        setVisible(true);
    }

}
