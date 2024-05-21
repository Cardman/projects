package cards.gui.containers;

import cards.gui.WindowCardsInt;
import code.gui.AbsPanel;
import code.gui.AbsTextArea;
import code.util.StringMap;

public interface ContainerPlayableGame {

//    SoftParams getParametres();
//    boolean isThreadAnime();
//    boolean isCarteSortie();
//    void setCarteSortie(boolean _carteSortie);
//    boolean isCarteEntree();
//    void setCarteEntree(boolean _carteEntree);
    WindowCardsInt getOwner();
    void showTricksHands();
//    StringMap<String> getMessages();

    StringMap<String> file();
//    String helpMenuTip();

    WindowCardsInt getWindow();

    AbsPanel getPanneauBoutonsJeu();

    AbsTextArea getEvents();
}
