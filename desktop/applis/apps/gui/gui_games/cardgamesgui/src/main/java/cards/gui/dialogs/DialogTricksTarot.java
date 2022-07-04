package cards.gui.dialogs;




import cards.gui.WindowCards;
import cards.gui.panels.PanelTricksHandsTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.TricksHandsTarot;
import code.gui.AbsScrollPane;

import code.gui.GuiConstants;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class DialogTricksTarot extends DialogCards {

    public DialogTricksTarot(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
    }
    public static void setDialogTricksTarot(String _titre, WindowCards _fenetre) {
        _fenetre.getDialogTricksTarot().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogTricksTarot().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogTricksTarot().getCardDialog().setTitle(_titre);
        _fenetre.getDialogTricksTarot().getCardDialog().setResizable(true);
        _fenetre.getDialogTricksTarot().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }
    public static void init(TricksHandsTarot _tricksHands, byte _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot, WindowCards _window) {
        _window.getDialogTricksTarot().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingTarot,_window);
    }
    private void setDialogue(TricksHandsTarot _tricksHands, byte _numberPlayers,
            StringList _pseudos, DisplayingTarot _displayingTarot, WindowCards _window) {

        _tricksHands.sortHands(_displayingTarot, _numberPlayers);
        AbsScrollPane scroll_ = _window.getCompoFactory().newAbsScrollPane(new PanelTricksHandsTarot(getCardDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingTarot,_window).getContainer());
        scroll_.setPreferredSize(new MetaDimension(600, 600));
        getCardDialog().setContentPane(scroll_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }

}
