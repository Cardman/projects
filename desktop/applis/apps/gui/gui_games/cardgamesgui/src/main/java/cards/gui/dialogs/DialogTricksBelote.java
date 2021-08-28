package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import cards.belote.DisplayingBelote;
import cards.belote.TricksHandsBelote;
import cards.gui.WindowCards;
import cards.gui.panels.PanelTricksHandsBelote;
import code.gui.AbsScrollPane;

import code.gui.initialize.AbsFrameFactory;
import code.util.StringList;

public final class DialogTricksBelote extends DialogCards {

    public DialogTricksBelote(AbsFrameFactory _frameFactory) {
        super(_frameFactory);
    }
    public static void setDialogTricksBelote(String _titre, WindowCards _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogTricksBelote().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogTricksBelote().getCardDialog().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogTricksBelote().getCardDialog().setTitle(_titre);
        _fenetre.getDialogTricksBelote().getCardDialog().setResizable(true);
        _fenetre.getDialogTricksBelote().getCardDialog().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsBelote _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingBelote _displayingBelote, WindowCards _ow) {
        _ow.getDialogTricksBelote().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingBelote, _ow);
    }

    private void setDialogue(TricksHandsBelote _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingBelote _displayingBelote, WindowCards _ow) {
        _tricksHands.sortHands(_displayingBelote, _numberPlayers);
        AbsScrollPane scroll_ = _ow.getCompoFactory().newAbsScrollPane(new PanelTricksHandsBelote(getCardDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingBelote, _ow).getContainer());
        scroll_.setPreferredSize(new Dimension(600, 600));
        getCardDialog().setContentPane(scroll_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }
}
