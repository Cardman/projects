package cards.gui.containers;

import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.AbsTextArea;

public interface ContainerPlayableTarot extends ContainerPlayableGame {
    void annonceTarotChelem();
    void prendreCartesChien();
    void validateDog();
    boolean isCanExcludeTrumps();
    HandTarot getCurrentIncludedTrumps();
    HandTarot getCurrentExcludedTrumps();
    Handfuls getChoosenHandful();
    AbsTextArea getInfoCurrentHandful();
    void displayTrumps();
    int required();

    CardTarot getCarteSurvoleeTarot();

    void setCarteSurvoleeTarot(CardTarot _c);
}
