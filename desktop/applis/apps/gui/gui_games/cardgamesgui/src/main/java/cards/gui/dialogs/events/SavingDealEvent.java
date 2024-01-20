package cards.gui.dialogs.events;



import cards.gui.WindowCardsInt;
import cards.gui.dialogs.SetterSelectedCardList;
import cards.gui.dialogs.enums.SaveDealMode;
import code.gui.events.AbsActionListener;
import code.scripts.messages.cards.MessagesEditorCards;
import code.util.core.StringUtil;

public class SavingDealEvent implements AbsActionListener {

    private final SetterSelectedCardList dialog;

    private final SaveDealMode mode;

    private final WindowCardsInt window;
    public SavingDealEvent(SetterSelectedCardList _dialog, SaveDealMode _mode, WindowCardsInt _window) {
        dialog = _dialog;
        mode = _mode;
        window = _window;
    }

    @Override
    public void action() {
        if (mode == SaveDealMode.SAVE_WITHOUT_CLOSING) {
            /*Si on veut sauvegarder une partie et on veut en creer une autre*/
            dialog.setPartie();
            //Methode permet de sauvegarder une partie et de relever d'eventuelles erreurs
            String fichier_=ok();
            if(!fichier_.isEmpty()) {
                dialog.cancelDeal();
            }
        } else if (mode == SaveDealMode.SAVE_THEN_PLAY) {
            /*Si on veut sauvegarder une partie puis la jouer et fermer l'editeur*/
            dialog.setPartie();
            String fichier_=ok();
            if (!fichier_.isEmpty()) {
                dialog.getEditorCards().doNotSetToNullGame();
                dialog.closeWindow();
                dialog.playGame();
            }
        } else if (mode == SaveDealMode.PLAY_WITHOUT_SAVING) {
            /*Si on veut jouer une partie sans la sauvegarder et fermer l'editeur*/
            dialog.setPartie();
            dialog.getEditorCards().doNotSetToNullGame();
            dialog.closeWindow();
            dialog.playGame();
        } else {
            //SAVE_THEN_CLOSE
            /*Si on veut sauvegarder une partie sans la jouer et fermer l'editeur*/
            dialog.setPartie();
            String fichier_=ok();
            if(!fichier_.isEmpty()) {
                dialog.closeWindow();
            }
        }
    }
    private String ok() {
        dialog.getEditorCards().getErrors().setText("");
        int stSize_ = dialog.stackSize();
        if (stSize_ != 0) {
            String lg_ = window.getLanguageKey();
            String mes_ = dialog.getEditorCards().translate(lg_,MessagesEditorCards.ERROR_REPARTITION);
            mes_ = StringUtil.simpleNumberFormat(mes_, stSize_);
            dialog.getEditorCards().getErrors().setText(mes_);
            return "";
        }
        dialog.getEditorCards().getSaveDialogContent().submitIfVisible();
        String fichier_=dialog.getEditorCards().getSaveDialogContent().getSelectedPath();
        if(!fichier_.isEmpty()) {
            dialog.validerSauvegarde(fichier_);
        }
        return fichier_;
    }
}
