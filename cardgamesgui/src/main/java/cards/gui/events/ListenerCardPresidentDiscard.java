package cards.gui.events;
import java.awt.Component;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerPresident;
import cards.president.enumerations.CardPresident;

public class ListenerCardPresidentDiscard extends
        AbstractListenerCardPresident {

    private ContainerPresident container;
    private boolean inHand;

    public ListenerCardPresidentDiscard(ContainerPresident _container,
            CardPresident _card, byte _index, boolean _inHand) {
        super(_container, _card, _index);
        container = _container;
        inHand = _inHand;
    }

    @Override
    protected boolean canListen() {
        return container.isCanDiscard();
    }

    @Override
    protected boolean playCardExited(MouseEvent _event) {
        if (inHand) {
            return _event.getPoint().y < 0;
        }
        Component c_ = _event.getComponent();
        if (c_ == null) {
            return false;
        }
        return _event.getPoint().y > c_.getHeight();
    }

    @Override
    protected void verifierRegles() {
        if (inHand) {
            container.discard(getIndexVerif());
        } else {
            container.cancelDiscard(getIndexVerif());
        }
    }

}
