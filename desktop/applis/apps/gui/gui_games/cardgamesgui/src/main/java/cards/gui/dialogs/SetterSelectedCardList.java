package cards.gui.dialogs;

import cards.gui.WindowCards;
import cards.gui.panels.CardsScrollableList;
import code.gui.AbsPanel;
import code.gui.Panel;
import code.gui.TextLabel;
import code.util.CustList;

public interface SetterSelectedCardList {

    int getNombreCartesSelectionnees();
    void setNombreCartesSelectionnees(int _nombreCartesSelectionnees);
    int getNombreCartesSelectionneesPrecedent();
    void setNombreCartesSelectionneesPrecedent(int _nombreCartesSelectionneesPrecedent);
    AbsPanel getPanelsCards();
    TextLabel getLabelSelectCards();
    CustList<CardsScrollableList> getHands(boolean _addStack);
    void validateRulesDeal(WindowCards _parent);
    void backToRules(WindowCards _parent);
    void deplacerCartes();
    void cancelDeal();
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
