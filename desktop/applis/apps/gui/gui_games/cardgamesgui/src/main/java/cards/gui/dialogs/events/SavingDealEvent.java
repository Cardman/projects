package cards.gui.dialogs.events;

import javax.swing.JOptionPane;

import cards.gui.WindowCards;
import cards.gui.dialogs.SetterSelectedCardList;
import cards.gui.dialogs.enums.SaveDealMode;
import code.gui.*;
import code.gui.events.AbsMouseListenerRel;

public class SavingDealEvent extends AbsMouseListenerRel {

    private SetterSelectedCardList dialog;

    private SaveDealMode mode;

    private WindowCards window;
    public SavingDealEvent(SetterSelectedCardList _dialog, SaveDealMode _mode, WindowCards _window) {
        dialog = _dialog;
        mode = _mode;
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        if (mode == SaveDealMode.SAVE_WITHOUT_CLOSING) {
            /*Si on veut sauvegarder une partie et on veut en creer une autre*/
            if(!dialog.isPartieSauvegardee()) {
                dialog.setPartie();
              //Methode permet de sauvegarder une partie et de relever d'eventuelles erreurs
                String fichier_=dialog.sauvegarder();
                if (fichier_ == null) {
                    dialog.releverErreurs();
                } else if(!fichier_.isEmpty()) {
                    dialog.setPartieSauvegardee(true);
                    dialog.cancelDeal();
                }
            } else {
                ConfirmDialog.showMessage((Dialog) dialog,dialog.getErrorSaveMessage(),dialog.getErrorSaveTitle(), window.getLanguageKey(), JOptionPane.ERROR_MESSAGE, window.getConfirmDialog());
                //JOptionPane.showMessageDialog(this,getMessages().getVal(ERROR_SAVE_FILE),getMessages().getVal(ERROR_SAVE_FILE_TITLE),JOptionPane.ERROR_MESSAGE);
            }
        } else if (mode == SaveDealMode.SAVE_THEN_PLAY) {
            /*Si on veut sauvegarder une partie puis la jouer et fermer l'editeur*/
            dialog.setPartie();
            String fichier_=dialog.sauvegarder();
            if (fichier_ == null) {
                dialog.releverErreurs();
            } else if (!fichier_.isEmpty()) {
                dialog.doNotSetToNullGame();
                dialog.closeWindow();
            }
        } else if (mode == SaveDealMode.PLAY_WITHOUT_SAVING) {
            /*Si on veut jouer une partie sans la sauvegarder et fermer l'editeur*/
            dialog.setPartie();
            dialog.doNotSetToNullGame();
            dialog.closeWindow();
        } else {
            //SAVE_THEN_CLOSE
            /*Si on veut sauvegarder une partie sans la jouer et fermer l'editeur*/
            dialog.setPartie();
            String fichier_=dialog.sauvegarder();
            if (fichier_ == null) {
                dialog.releverErreurs();
            } else if(!fichier_.isEmpty()) {
                dialog.closeWindow();
            }
        }
    }
}
