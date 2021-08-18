package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import cards.gui.WindowCards;
import cards.gui.panels.PanelTricksHandsTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.TricksHandsTarot;
import code.gui.ScrollPane;
import code.util.StringList;

public final class DialogTricksTarot extends DialogCards {

    public static void setDialogTricksTarot(String _titre, WindowCards _fenetre) {
        _fenetre.getDialogTricksTarot().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogTricksTarot().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogTricksTarot().setTitle(_titre);
        _fenetre.getDialogTricksTarot().setResizable(true);
        _fenetre.getDialogTricksTarot().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
    public static void init(TricksHandsTarot _tricksHands, byte _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot, WindowCards _window) {
        _window.getDialogTricksTarot().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingTarot,_window);
    }
    private void setDialogue(TricksHandsTarot _tricksHands, byte _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot, WindowCards _window) {

        _tricksHands.sortHands(_displayingTarot, _numberPlayers);
        ScrollPane scroll_ = new ScrollPane(new PanelTricksHandsTarot(this,
                _tricksHands, _numberPlayers, _pseudos, _displayingTarot,_window).getContainer());
        scroll_.setPreferredSize(new Dimension(600, 600));
        setContentPane(scroll_);
        pack();
        setVisible(true);
    }

}
