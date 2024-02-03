package cards.gui.containers;

import cards.facade.SoftParams;
import cards.gui.WindowCardsInt;
import code.util.StringMap;

public interface ContainerPlayableGame extends Containable {

    SoftParams getParametres();
    boolean isThreadAnime();
    boolean isCarteSortie();
    void setCarteSortie(boolean _carteSortie);
    boolean isCarteEntree();
    void setCarteEntree(boolean _carteEntree);
    WindowCardsInt getOwner();
    void showTricksHands();
    String getRaisonCourante();
    StringMap<String> getMessages();

    boolean isaJoueCarte();

    void setaJoueCarte(boolean _b);

    void setRaisonCourante(String _s);

    StringMap<String> file();
    String helpMenuTip();
}
