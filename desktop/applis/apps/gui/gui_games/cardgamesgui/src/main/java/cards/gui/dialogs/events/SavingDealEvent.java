package cards.gui.dialogs.events;


import cards.gui.dialogs.SetterSelectedCardList;
import cards.gui.dialogs.enums.SaveDealMode;
import code.gui.events.AbsActionListener;
import code.scripts.messages.cards.MessagesEditorCards;
import code.util.core.StringUtil;

public class SavingDealEvent implements AbsActionListener {

    private final SetterSelectedCardList dialog;

    private final SaveDealMode mode;

    public SavingDealEvent(SetterSelectedCardList _dialog, SaveDealMode _mode) {
        dialog = _dialog;
        mode = _mode;
    }

    @Override
    public void action() {
        dialog.setPartie();
        dialog.getEditorCards().getErrors().setText("");
        if (!dialog.okDeal()) {
            errors(dialog.getEditorCards().getAll().first().taille());
            return;
        }
        String fichier_=ok();
        if(fichier_.isEmpty()) {
            return;
        }
        if (mode == SaveDealMode.SAVE_WITHOUT_CLOSING) {
            /*Si on veut sauvegarder une partie et on veut en creer une autre*/
            //Methode permet de sauvegarder une partie et de relever d'eventuelles erreurs
            dialog.cancelDeal();
        } else if (mode == SaveDealMode.SAVE_THEN_CLOSE) {
            /*Si on veut sauvegarder une partie sans la jouer et fermer l'editeur*/
            dialog.closeWindow();
        } else {
            //mode == SaveDealMode.SAVE_THEN_PLAY || mode == SaveDealMode.PLAY_WITHOUT_SAVING
            /*Si on veut sauvegarder une partie puis la jouer et fermer l'editeur*/
            /*Si on veut jouer une partie sans la sauvegarder et fermer l'editeur*/
            dialog.getEditorCards().doNotSetToNullGame();
            dialog.closeWindow();
            dialog.playGame();
        }
    }
    private String ok() {
        if (mode == SaveDealMode.PLAY_WITHOUT_SAVING) {
            return "_";
        }
        dialog.getEditorCards().getSaveDialogContent().submitIfVisible();
        String fichier_=dialog.getEditorCards().getSaveDialogContent().getSelectedPath();
        if(!fichier_.isEmpty()) {
            dialog.validerSauvegarde(fichier_);
        }
        return fichier_;
    }

    private void errors(int _stSize) {
        String mes_ = dialog.getEditorCards().translate(MessagesEditorCards.ERROR_REPARTITION);
        mes_ = StringUtil.simpleNumberFormat(mes_, _stSize);
        dialog.getEditorCards().getErrors().setText(mes_);
    }
}
