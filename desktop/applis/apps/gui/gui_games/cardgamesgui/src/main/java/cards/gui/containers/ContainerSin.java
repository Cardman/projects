package cards.gui.containers;

import cards.consts.GameType;
import cards.gui.WindowCards;
import code.gui.AbsButton;
import code.threads.AbstractAtomicInteger;

public interface ContainerSin extends ContainerPlayableGame{
    AbsButton getNextDeal();
    void setNextDeal(AbsButton _b);

    void keepPlayingRandom();

    void keepPlayingEdited();

    void stopPlaying();

    void replay();

    void modify();

    void aideAuJeu();

    void conseil();

//    boolean isPasse();
//    void setPasse(boolean _passe);

    AbstractAtomicInteger getPaused();
    AbstractAtomicInteger getAnimated();
    WindowCards window();
    GameType getGameType();
    long nombreParties();
    long nombreTotalParties();
}
