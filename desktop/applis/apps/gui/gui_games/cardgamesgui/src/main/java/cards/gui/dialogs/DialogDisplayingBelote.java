package cards.gui.dialogs;





import cards.belote.DisplayingBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.Order;
import cards.consts.Suit;
import cards.facade.FacadeCards;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesGuiCards;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DialogDisplayingBelote extends DialogHelpCards implements DialogDisplaying {

    private final DialogDisplayingContent dialogDisplayingContent;
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private AbsCustCheckBox sortByTrump;

    public DialogDisplayingBelote(AbstractProgramInfos _frameFactory, EnabledMenu _menu) {
        super(_frameFactory);
        dialogDisplayingContent = new DialogDisplayingContent(Suit.couleursOrdinaires(), _menu);
    }
    public static void setDialogDisplayingBelote(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
//        _fenetre.getDialogDisplayingBelote().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogDisplayingBelote().getDialogDisplayingContent().getAssociated().setEnabled(false);
        _fenetre.getDialogDisplayingBelote().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogDisplayingBelote().displayingBelote = _fenetre.getDisplayingBelote();
        _fenetre.getDialogDisplayingBelote().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogDisplayingBelote().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        _fenetre.getDialogDisplayingBelote().setDialogue(_fenetre);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        getDialogDisplayingContent().getAssociated().setEnabled(true);
    }

    public static DisplayingBelote getDisplaying(DialogDisplayingBelote _dialog) {
        _dialog.getAbsDialog().setVisible(true);
        return _dialog.displayingBelote;
    }

    public void setDialogue(WindowCardsInt _window) {
        AbsPanel panel_ = dialogDisplayingContent.setDialogue(_window, this, displayingBelote.getDisplaying());
        StringMap<String> mess_ = Games.getDialogDisplayTr(Games.getAppliTr(_window.getFrames().currentLg())).getMapping();
        //Panneau Tri avant enchere (Atout)
        AbsPanel sousPanneau_=_window.getCompoFactory().newPageBox();
        sousPanneau_.add(getCompoFactory().newPlainLabel(mess_.getVal(MessagesGuiCards.DIAL_DISPLAY_SORTING_BEFORE_PLAYING_CARDS)));
        sortByTrump=getCompoFactory().newCustCheckBox(mess_.getVal(MessagesGuiCards.DIAL_DISPLAY_SORTING_TRUMP));
        sortByTrump.setSelected(displayingBelote.getOrderBeforeBids()==Order.TRUMP);
        sousPanneau_.add(sortByTrump);
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
        _w.baseWindow().getFacadeCards().setDisplayingBelote(displayingBelote);
        StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(_w.getFrames()), FacadeCards.DISPLAY_BELOTE), DocumentWriterBeloteUtil.setDisplayingBelote(_w.baseWindow().getFacadeCards().getDisplayingBelote()),_w.getStreams());
        _w.baseWindow().getContainerGame().setDisplayingBelote( _w.baseWindow().getFacadeCards().getDisplayingBelote());
    }

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    @Override
    public void validateDisplaying() {
        if(sortByTrump.isSelected()) {
            displayingBelote.setOrderBeforeBids(Order.TRUMP);
        } else {
            displayingBelote.setOrderBeforeBids(Order.SUIT);
        }
        closeWindow();
    }

    public AbsCustCheckBox getSortByTrump() {
        return sortByTrump;
    }
}

