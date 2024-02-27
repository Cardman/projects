package cards.gui.dialogs;

import cards.gui.WindowCardsInt;
import code.gui.AbsCommonFrame;

public interface SetterSelectedCardList {

    AbsCommonFrame getAbsDialog();
    EditorCards getEditorCards();
    void validateRulesDeal(WindowCardsInt _parent);
    void backToRules(WindowCardsInt _parent);
    void deplacerCartes();
    void cancelDeal();

    void setPartie();
    void playGame();
    void validerSauvegarde(String _s);
    boolean okDeal();
    void closeWindow();

}
