package cards.gui.events;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.DiscardedCard;
import cards.tarot.enumerations.CardTarot;

public class ListenerCardTarotMultiDog extends AbstractListenerCard<CardTarot> {

    private final ContainerMultiTarot container;

    public ListenerCardTarotMultiDog(ContainerMultiTarot _container, CardTarot _pcarte) {
        super(_container, _pcarte);
        container = _container;
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
        CardTarot selected_ = getCard();
        DiscardedCard discard_ = new DiscardedCard();
        discard_.setCard(selected_);
        discard_.setPlace(container.getIndexInGame());
        discard_.setInHand(container.getTakerCardsDog().contient(getCard()));
        String lg_ = container.getOwner().getLanguageKey();
        discard_.setLocale(lg_);
        container.window().sendObject(discard_);

    }

}
