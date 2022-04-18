package cards.gui.containers;

public interface ContainerPlayableBelote extends ContainerPlayableGame {
    void bid();
    void fold();
    void invertBeloteRebelote();
    void invertBeloteDeclare();
}
