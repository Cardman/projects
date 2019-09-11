package cards.gui.events;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerMultiTarot;
import cards.gui.labels.GraphicTarotCard;
import cards.network.tarot.actions.DiscardedCard;
import cards.tarot.enumerations.CardTarot;

public class ListenerCardTarotMultiDog extends AbstractListenerCardTarot {

    private ContainerMultiTarot container;
    private boolean inHand;
    private GraphicTarotCard component;
    public ListenerCardTarotMultiDog(ContainerMultiTarot _container, CardTarot _pcarte,boolean _inHand, GraphicTarotCard _component) {
        super(_container, _pcarte);
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
        CardTarot selected_ = getCarteVerif();
        DiscardedCard discard_ = new DiscardedCard();
        discard_.setCard(selected_);
        discard_.setPlace(container.getIndexInGame());
        discard_.setInHand(container.getTakerCardsDog().contient(getCarteVerif()));
        String lg_ = container.getOwner().getLanguageKey();
        discard_.setLocale(lg_);
        container.getOwner().sendObject(discard_);

    }

}
