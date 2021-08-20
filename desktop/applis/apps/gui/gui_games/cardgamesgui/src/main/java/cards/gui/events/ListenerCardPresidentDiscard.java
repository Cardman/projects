package cards.gui.events;

import cards.gui.containers.ContainerPresident;
import cards.gui.labels.GraphicPresidentCard;
import cards.president.enumerations.CardPresident;
import code.gui.AbsMouseLocation;

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
    protected boolean playCardExited(AbsMouseLocation _event) {
        if (inHand) {
            return _event.getYcoord() < 0;
        }
        return _event.getYcoord() > component.getHeight();
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
