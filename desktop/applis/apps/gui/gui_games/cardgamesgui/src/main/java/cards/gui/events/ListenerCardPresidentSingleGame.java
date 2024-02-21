package cards.gui.events;



import cards.gui.containers.ContainerSinglePresident;
import cards.president.enumerations.CardPresident;

public class ListenerCardPresidentSingleGame extends
        AbstractListenerCardPresident {

    private final ContainerSinglePresident container;

    public ListenerCardPresidentSingleGame(ContainerSinglePresident _container,
            CardPresident _card, byte _index) {
        super(_container, _card, _index);
        container = _container;
    }

//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        return _event.getYcoord() < 0;
//    }

    @Override
    protected void verifierRegles() {
        container.finPliPresident(getCarteVerif(), getIndexVerif());
    }
}
