package cards.gui.containers;

import cards.gui.WindowCards;

public interface ContainerSingle extends ContainerPlayableGame {

    void nextTrick();

    void endDeal();

    void keepPlayingRandom();

    void keepPlayingEdited();

    void stopPlaying();

    void replay();

    void modify();

    void aideAuJeu();

    void conseil();

    boolean isPasse();
    void setPasse(boolean _passe);
    WindowCards window();
}
