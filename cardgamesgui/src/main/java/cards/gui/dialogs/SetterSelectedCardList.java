package cards.gui.dialogs;
import javax.swing.JLabel;

import cards.gui.MainWindow;
import cards.gui.panels.CardsScrollableList;
import code.gui.Panel;
import code.util.CustList;

public interface SetterSelectedCardList {

    int getNombreCartesSelectionnees();
    void setNombreCartesSelectionnees(int _nombreCartesSelectionnees);
    int getNombreCartesSelectionneesPrecedent();
    void setNombreCartesSelectionneesPrecedent(int _nombreCartesSelectionneesPrecedent);
    Panel getPanelsCards();
    JLabel getLabelSelectCards();
    CustList<CardsScrollableList> getHands(boolean _addStack);
    void validateRulesDeal(MainWindow _parent);
    void backToRules(MainWindow _parent);
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
