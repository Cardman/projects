package cards.gui.dialogs;




import cards.gui.WindowCards;
import cards.gui.panels.PanelTricksHandsPresident;
import cards.president.DisplayingPresident;
import cards.president.TricksHandsPresident;
import code.gui.AbsScrollPane;

import code.gui.GuiConstants;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsFrameFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class DialogTricksPresident extends DialogCards {

    public DialogTricksPresident(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
    }
    public static void setDialogTricksPresident(String _titre, WindowCards _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogTricksPresident().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogTricksPresident().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogTricksPresident().getCardDialog().setTitle(_titre);
        _fenetre.getDialogTricksPresident().getCardDialog().setResizable(true);
        _fenetre.getDialogTricksPresident().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }

    public static void init(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, WindowCards _window) {
        _window.getDialogTricksPresident().setDialogue(_tricksHands, _numberPlayers, _pseudos, _displayingPresident,_window);
    }

    private void setDialogue(TricksHandsPresident _tricksHands,
            byte _numberPlayers, StringList _pseudos,
            DisplayingPresident _displayingPresident, WindowCards _window) {
        _tricksHands.sortHands(_displayingPresident, _numberPlayers);
        AbsScrollPane scroll_ = _window.getCompoFactory().newAbsScrollPane(new PanelTricksHandsPresident(getCardDialog(),
                _tricksHands, _numberPlayers, _pseudos, _displayingPresident,_window).getContainer());
        scroll_.setPreferredSize(new MetaDimension(600, 600));
        getCardDialog().setContentPane(scroll_);
        getCardDialog().pack();
        getCardDialog().setVisible(true);
    }
}
