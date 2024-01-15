package cards.gui.dialogs;

import cards.gui.WindowCardsInt;

public interface SetterSelectedCardList {

    EditorCards getEditorCards();
    void validateRulesDeal(WindowCardsInt _parent);
    void backToRules(WindowCardsInt _parent);
    void deplacerCartes();
    void cancelDeal();

    void setPartie();
    String sauvegarder();
    void releverErreurs();
    void closeWindow();

}
