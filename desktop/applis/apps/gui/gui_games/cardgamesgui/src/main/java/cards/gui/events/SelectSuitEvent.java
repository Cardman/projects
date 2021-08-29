package cards.gui.events;

import cards.belote.BidBeloteSuit;
import cards.gui.containers.ContainerBelote;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class SelectSuitEvent extends AbsMouseListenerRel {

    private final ContainerBelote container;

    private final BidBeloteSuit suit;

    public SelectSuitEvent(ContainerBelote _container, BidBeloteSuit _suit) {
        container = _container;
        suit = _suit;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        container.setBid(suit);
    }
}
