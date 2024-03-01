package cards.gui.containers;

import cards.gui.labels.IntCardConverter;

public interface ContainerSingle<T> extends ContainerPlayableGame {

//    void nextTrick();

//    void endDeal();

    IntCardConverter<T> converter();
}
