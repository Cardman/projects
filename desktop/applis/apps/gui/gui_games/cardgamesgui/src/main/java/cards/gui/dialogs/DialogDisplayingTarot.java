package cards.gui.dialogs;





import cards.consts.Suit;
import cards.facade.MessagesCardGames;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.tarot.DisplayingTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamTextFile;
import code.util.core.StringUtil;

public final class DialogDisplayingTarot extends DialogHelpCards implements DialogDisplaying {

    private final DialogDisplayingContent dialogDisplayingContent;
    private DisplayingTarot displayingTarot;

    public DialogDisplayingTarot(AbstractProgramInfos _frameFactory, EnabledMenu _menu) {
        super(_frameFactory);
        dialogDisplayingContent = new DialogDisplayingContent(Suit.couleursDefinies(), _menu);
    }
    public static void setDialogDisplayingTarot(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
//        _fenetre.getDialogDisplayingTarot().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogDisplayingTarot().getDialogDisplayingContent().getAssociated().setEnabled(false);
        _fenetre.getDialogDisplayingTarot().setTitleDialog(_fenetre,_titre);
//        _fenetre.getDialogDisplayingTarot().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogDisplayingTarot().displayingTarot = _fenetre.getDisplayingTarot();
//        _fenetre.getDialogDisplayingTarot().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogDisplayingTarot().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        _fenetre.getDialogDisplayingTarot().setDialogue(_fenetre);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        getDialogDisplayingContent().getAssociated().setEnabled(true);
    }
    public static DisplayingTarot getDisplaying(DialogDisplayingTarot _dialog) {
        _dialog.getAbsDialog().setVisible(true);
        return _dialog.displayingTarot;
    }

    public void setDialogue(WindowCardsInt _window) {
        AbsPanel panel_ = dialogDisplayingContent.setDialogue(_window, this, displayingTarot.getDisplaying());
        getAbsDialog().setContentPane(panel_);
        getAbsDialog().pack();
    }

    @Override
    public DialogDisplayingContent getDialogDisplayingContent() {
        return dialogDisplayingContent;
    }
    @Override
    public void savePrefs(WindowCardsInt _w) {
        closeWindow();
        _w.baseWindow().getFacadeCards().setDisplayingTarot(displayingTarot);
        StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(_w.getFrames()), MessagesCardGames.getAppliFilesTr(_w.getFrames().getTranslations()).val().getMapping().getVal(MessagesCardGames.DISPLAY_TAROT)), DocumentWriterTarotUtil.setDisplayingTarot(_w.baseWindow().getFacadeCards().getDisplayingTarot()),_w.getStreams());
        _w.baseWindow().getContainerGame().setDisplayingTarot(_w.baseWindow().getFacadeCards().getDisplayingTarot());
    }

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    @Override
    public void validateDisplaying() {
        closeWindow();

    }

}
