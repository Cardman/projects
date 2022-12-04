package cards.gui.dialogs;

import cards.gui.WindowCardsInt;
import cards.gui.panels.CardsScrollableList;
import code.gui.AbsPanel;

import code.gui.AbsPlainLabel;
import code.util.CustList;

public interface SetterSelectedCardList {

    int getNombreCartesSelectionnees();
    void setNombreCartesSelectionnees(int _nombreCartesSelectionnees);
    int getNombreCartesSelectionneesPrecedent();
    void setNombreCartesSelectionneesPrecedent(int _nombreCartesSelectionneesPrecedent);
    AbsPanel getPanelsCards();
    AbsPlainLabel getLabelSelectCards();
    CustList<CardsScrollableList> getHands(boolean _addStack);
    void validateRulesDeal(WindowCardsInt _parent);
    void backToRules(WindowCardsInt _parent);
    void deplacerCartes();
    void cancelDeal();
    boolean isSetToNullGame();
    void setPartie();
    String sauvegarder();
    void releverErreurs();
    void closeWindow();
    boolean isPartieSauvegardee();
    void setPartieSauvegardee(boolean _b);
    void doNotSetToNullGame();
    String getErrorSaveTitle();
    String getErrorSaveMessage();
}
