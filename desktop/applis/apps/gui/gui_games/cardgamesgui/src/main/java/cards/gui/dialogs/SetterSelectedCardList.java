package cards.gui.dialogs;

import cards.gui.WindowCardsInt;

public interface SetterSelectedCardList {

    EditorCards getEditorCards();
    void validateRulesDeal(WindowCardsInt _parent);
    void backToRules(WindowCardsInt _parent);
    void deplacerCartes();
    void cancelDeal();

    void setPartie();
    void playGame();
    void validerSauvegarde(String _s);
    int stackSize();
    boolean okDeal();
    void closeWindow();

}
