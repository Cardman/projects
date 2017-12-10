package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import cards.belote.DisplayingBelote;
import cards.belote.TricksHandsBelote;
import cards.gui.MainWindow;
import cards.gui.panels.PanelTricksHandsBelote;
import code.util.StringList;

public final class DialogTricksBelote extends DialogCards {

    private static final DialogTricksBelote DIALOG = new DialogTricksBelote();

    private DialogTricksBelote() {
    }

    public static void setDialogTricksBelote(String _titre, MainWindow _fenetre) {
        //super(_titre, _fenetre, true);
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.setTitle(_titre);
        DIALOG.setResizable(true);
        DIALOG.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsBelote _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingBelote _displayingBelote) {
        DIALOG.setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingBelote);
    }

    private void setDialogue(TricksHandsBelote _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingBelote _displayingBelote) {
        _tricksHands.sortHands(_displayingBelote, _numberPlayers);
        JScrollPane scroll_ = new JScrollPane(new PanelTricksHandsBelote(this,
                _tricksHands, _numberPlayers, _pseudos, _displayingBelote));
        scroll_.setPreferredSize(new Dimension(600, 600));
        setContentPane(scroll_);
        pack();
        setVisible(true);
    }
}
