package cards.gui.dialogs;





import cards.consts.Suit;
import cards.facade.FacadeCards;
import cards.facade.Games;
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

public final class DialogDisplayingPresident extends DialogCards implements DialogDisplaying {

    private final DialogDisplayingContent dialogDisplayingContent;
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private AbsSpinner nbDealsDemo;

    public DialogDisplayingPresident(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
        dialogDisplayingContent = new DialogDisplayingContent(Suit.couleursOrdinaires());
    }
    public static void setDialogDisplayingPresident(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogDisplayingPresident().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogDisplayingPresident();
        _fenetre.getDialogDisplayingPresident().getCardDialog().setTitle(_titre);
        _fenetre.getDialogDisplayingPresident().displayingPresident = _fenetre.getDisplayingPresident();
        _fenetre.getDialogDisplayingPresident().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogDisplayingPresident().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        _fenetre.getDialogDisplayingPresident().setDialogue(_fenetre);
    }

    public static DisplayingPresident getDisplaying(DialogDisplayingPresident _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.displayingPresident;
    }

    public void setDialogue(WindowCardsInt _window) {
        StringMap<String> mess_ = Games.getDialogDisplayTr(Games.getAppliTr(_window.getFrames().currentLg())).getMapping();
        AbsPanel panel_ = dialogDisplayingContent.setDialogue(_window, this, displayingPresident.getDisplaying());
        //Panneau Tri avant enchere (Atout)
        AbsPanel sousPanneau_=_window.getCompoFactory().newGrid(0,1);
        sousPanneau_.add(getCompoFactory().newPlainLabel(mess_.getVal(MessagesGuiCards.DIAL_DISPLAY_NB_DEALS_DEMO)));
        //Panneau Distribution
        nbDealsDemo = getCompoFactory().newSpinner(displayingPresident.getNbDeals(),FileConst.MIN_DEALS,FileConst.MAX_DEALS,1);
        sousPanneau_.add(nbDealsDemo);
        dialogDisplayingContent.getCenter().add(sousPanneau_);
        getCardDialog().setContentPane(panel_);
        getCardDialog().pack();
    }

    @Override
    public DialogDisplayingContent getDialogDisplayingContent() {
        return dialogDisplayingContent;
    }
    @Override
    public void savePrefs(WindowCardsInt _w) {
        _w.baseWindow().getFacadeCards().setDisplayingPresident(displayingPresident);
        StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(_w.getFrames()), FacadeCards.DISPLAY_PRESIDENT), DocumentWriterPresidentUtil.setDisplayingPresident(_w.baseWindow().getFacadeCards().getDisplayingPresident()),_w.getStreams());
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

