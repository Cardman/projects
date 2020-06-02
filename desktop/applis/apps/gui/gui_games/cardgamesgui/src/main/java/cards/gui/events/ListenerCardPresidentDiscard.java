package cards.gui.events;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerPresident;
import cards.gui.labels.GraphicPresidentCard;
import cards.president.enumerations.CardPresident;

public class ListenerCardPresidentDiscard extends
        AbstractListenerCardPresident {

    private ContainerPresident container;
    private boolean inHand;
    private GraphicPresidentCard component;

    public ListenerCardPresidentDiscard(ContainerPresident _container,
            CardPresident _card, byte _index, boolean _inHand, GraphicPresidentCard _component) {
        super(_container, _card, _index);
        container = _container;
        inHand = _inHand;
        component = _component;
    }

    @Override
    protected boolean canListen() {
        return container.isCanDiscard();
    }

    @Override
    protected boolean playCardExited(MouseEvent _event) {
        if (inHand) {
            return _event.getY() < 0;
        }
        return _event.getY() > component.getHeight();
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
