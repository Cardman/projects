package cards.gui.dialogs;





import cards.consts.Suit;
import cards.facade.MessagesCardGames;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.president.DisplayingPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesGuiCards;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DialogDisplayingPresident extends DialogHelpCards implements DialogDisplaying {

    private final DialogDisplayingContent dialogDisplayingContent;
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private AbsSpinner nbDealsDemo;

    public DialogDisplayingPresident(AbstractProgramInfos _frameFactory, EnabledMenu _menu) {
        super(_frameFactory);
        dialogDisplayingContent = new DialogDisplayingContent(Suit.couleursOrdinaires(), _menu);
    }
    public static void setDialogDisplayingPresident(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
//        _fenetre.getDialogDisplayingPresident().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogDisplayingPresident().getDialogDisplayingContent().getAssociated().setEnabled(false);
        _fenetre.getDialogDisplayingPresident().setTitleDialog(_fenetre,_titre);
//        _fenetre.getDialogDisplayingPresident().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogDisplayingPresident().displayingPresident = _fenetre.getDisplayingPresident();
//        _fenetre.getDialogDisplayingPresident().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogDisplayingPresident().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        _fenetre.getDialogDisplayingPresident().setDialogue(_fenetre);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        getDialogDisplayingContent().getAssociated().setEnabled(true);
    }
    public static DisplayingPresident getDisplaying(DialogDisplayingPresident _dialog) {
        _dialog.getAbsDialog().setVisible(true);
        return _dialog.displayingPresident;
    }

    public void setDialogue(WindowCardsInt _window) {
        StringMap<String> mess_ = MessagesCardGames.getDialogDisplayTr(MessagesCardGames.getAppliTr(_window.getFrames().currentLg())).getMapping();
        AbsPanel panel_ = dialogDisplayingContent.setDialogue(_window, this, displayingPresident.getDisplaying());
        //Panneau Tri avant enchere (Atout)
        AbsPanel sousPanneau_=_window.getCompoFactory().newPageBox();
        sousPanneau_.add(getCompoFactory().newPlainLabel(mess_.getVal(MessagesGuiCards.DIAL_DISPLAY_NB_DEALS_DEMO)));
        //Panneau Distribution
        nbDealsDemo = getCompoFactory().newSpinner(displayingPresident.getNbDeals(), EditorCards.MIN_DEALS, EditorCards.MAX_DEALS,1);
        sousPanneau_.add(nbDealsDemo);
        dialogDisplayingContent.getCenter().add(sousPanneau_);
        getAbsDialog().setContentPane(panel_);
        getAbsDialog().pack();
    }

    @Override
    public DialogDisplayingContent getDialogDisplayingContent() {
        return dialogDisplayingContent;
    }
    @Override
    public void savePrefs(WindowCardsInt _w) {
        _w.baseWindow().getFacadeCards().setDisplayingPresident(displayingPresident);
        StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(_w.getFrames()), MessagesCardGames.getAppliFilesTr(_w.getFrames().getTranslations()).val().getMapping().getVal(MessagesCardGames.DISPLAY_PRESIDENT)), DocumentWriterPresidentUtil.setDisplayingPresident(_w.baseWindow().getFacadeCards().getDisplayingPresident()),_w.getStreams());
        _w.baseWindow().getContainerGame().setDisplayingPresident(_w.baseWindow().getFacadeCards().getDisplayingPresident());
    }

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    @Override
    public void validateDisplaying() {
        displayingPresident.setNbDeals(nbDealsDemo.getValue());
        closeWindow();
    }

    public AbsSpinner getNbDealsDemo() {
        return nbDealsDemo;
    }
}

