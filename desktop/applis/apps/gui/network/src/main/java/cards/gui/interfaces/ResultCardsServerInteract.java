package cards.gui.interfaces;

import cards.facade.enumerations.GameEnum;
import code.network.WindowNetWork;

public interface ResultCardsServerInteract {
    ResultCardsServer interact(WindowNetWork _app, GameEnum _jeuBouton);
}
