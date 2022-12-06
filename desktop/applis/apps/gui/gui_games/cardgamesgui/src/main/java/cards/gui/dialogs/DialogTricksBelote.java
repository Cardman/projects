package cards.gui.dialogs;




import cards.belote.DisplayingBelote;
import cards.belote.TricksHandsBelote;
import cards.gui.WindowCardsInt;
import cards.gui.panels.PanelTricksHandsBelote;
import code.gui.*;

import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class DialogTricksBelote extends DialogCards {

    public DialogTricksBelote(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
    }
    public static void setDialogTricksBelote(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogTricksBelote().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogTricksBelote().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogTricksBelote().getCardDialog().setTitle(_titre);
        _fenetre.getDialogTricksBelote().getCardDialog().setResizable(true);
//        _fenetre.getDialogTricksBelote().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsBelote _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingBelote _displayingBelote, WindowCardsInt _ow) {
        _ow.getDialogTricksBelote().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingBelote, _ow);
    }

    private void setDialogue(TricksHandsBelote _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingBelote _displayingBelote, WindowCardsInt _ow) {
        _tricksHands.sortHands(_displayingBelote, _numberPlayers);
        AbsScrollPane scroll_ = _ow.getCompoFactory().newAbsScrollPane(new PanelTricksHandsBelote(getCardDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingBelote, _ow).getContainer());
        scroll_.setPreferredSize(new MetaDimension(600, 600));
        getCardDialog().setContentPane(scroll_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }
}
