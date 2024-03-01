package cards.gui.containers;


public interface ContainerSinglePausable<T> extends ContainerSin,ContainerSingle<T> {

    void firstTrick();
    void nextTrick();

    void endDeal();

}
