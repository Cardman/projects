package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.belote.BidBeloteSuit;
import cards.gui.containers.ContainerBelote;

public class SelectSuitEvent extends MouseAdapter {

    private ContainerBelote container;

    private BidBeloteSuit suit;

    public SelectSuitEvent(ContainerBelote _container, BidBeloteSuit _suit) {
        container = _container;
        suit = _suit;
    }

    @Override
    public void mouseReleased(MouseEvent _arg0) {
        container.setBid(suit);
    }
}
