package cards.gui.dialogs;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.util.CustList;
import cards.gui.panels.CardsScrollableList;

public interface SetterSelectedCardList {

    int getNombreCartesSelectionnees();
    void setNombreCartesSelectionnees(int _nombreCartesSelectionnees);
    int getNombreCartesSelectionneesPrecedent();
    void setNombreCartesSelectionneesPrecedent(int _nombreCartesSelectionneesPrecedent);
    JPanel getPanelsCards();
    JLabel getLabelSelectCards();
    CustList<CardsScrollableList> getHands(boolean _addStack);
    void validateRulesDeal();
    void backToRules();
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
