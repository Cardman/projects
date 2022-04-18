package cards.gui.containers;

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
}
