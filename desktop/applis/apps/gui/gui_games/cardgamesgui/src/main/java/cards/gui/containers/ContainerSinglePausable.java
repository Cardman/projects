package cards.gui.containers;


public interface ContainerSinglePausable<T> extends ContainerSin,ContainerSingle<T> {

    void nextTrick();

    void endDeal();

}
