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

    public static void setDialogTricksBelote(String _titre, MainWindow _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogTricksBelote().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogTricksBelote().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogTricksBelote().setTitle(_titre);
        _fenetre.getDialogTricksBelote().setResizable(true);
        _fenetre.getDialogTricksBelote().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsBelote _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingBelote _displayingBelote, MainWindow _ow) {
        _ow.getDialogTricksBelote().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingBelote, _ow);
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
