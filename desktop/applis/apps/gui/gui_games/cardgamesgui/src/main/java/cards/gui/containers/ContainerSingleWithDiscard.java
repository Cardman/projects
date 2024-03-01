package cards.gui.containers;

import code.gui.AbsPanel;
import code.util.IdList;

public interface ContainerSingleWithDiscard<T> extends ContainerSin,ContainerSingle<T>{
    IdList<T> ecartables();
    IdList<T> hand();
    AbsPanel getPanelHand();
    AbsPanel getCenterDeck();
    IdList<T> discarded();
    void discard(T _t);
    void restore(T _t);
    String errMessage(IdList<T> _must,T _t);
    void afterHands();
}
