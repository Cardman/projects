package cards.gui.interfaces;

import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;

public interface ResultCardsServerInteract {
    ResultCardsServer interact(WindowCards _app, GameEnum _jeuBouton);
}
