package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import cards.belote.DisplayingBelote;
import cards.belote.TricksHandsBelote;
import cards.gui.MainWindow;
import cards.gui.panels.PanelTricksHandsBelote;
import code.gui.ScrollPane;
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
            DisplayingBelote _displayingBelote, MainWindow _ow) {
        DIALOG.setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingBelote, _ow);
    }

    private void setDialogue(TricksHandsBelote _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingBelote _displayingBelote, MainWindow _ow) {
        _tricksHands.sortHands(_displayingBelote, _numberPlayers);
        ScrollPane scroll_ = new ScrollPane(new PanelTricksHandsBelote(this,
                _tricksHands, _numberPlayers, _pseudos, _displayingBelote, _ow).getContainer());
        scroll_.setPreferredSize(new Dimension(600, 600));
        setContentPane(scroll_);
        pack();
        setVisible(true);
    }
}
