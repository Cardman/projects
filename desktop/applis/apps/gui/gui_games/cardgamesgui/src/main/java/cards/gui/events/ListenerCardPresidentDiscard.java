package cards.gui.events;

import cards.gui.containers.ContainerPlayablePresident;
import cards.president.enumerations.CardPresident;

public class ListenerCardPresidentDiscard extends
        AbstractListenerCard<CardPresident> {

    private final ContainerPlayablePresident container;
    private final boolean inHand;

    public ListenerCardPresidentDiscard(ContainerPlayablePresident _container,
                                        CardPresident _card, int _index, boolean _inHand) {
        super(_container, _card, _index);
        container = _container;
        inHand = _inHand;
    }

//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        if (inHand) {
//            return _event.getYcoord() < 0;
//        }
//        return _event.getYcoord() > component.getHeight();
//    }

    @Override
    protected void verifierRegles() {
        if (inHand) {
            container.discard(getIndex());
        } else {
            container.cancelDiscard(getIndex());
        }
    }

}
