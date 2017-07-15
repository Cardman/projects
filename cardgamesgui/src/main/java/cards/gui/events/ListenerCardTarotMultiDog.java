package cards.gui.events;
import java.awt.Component;
import java.awt.event.MouseEvent;

import code.util.consts.Constants;
import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.DiscardedCard;
import cards.tarot.enumerations.CardTarot;

public class ListenerCardTarotMultiDog extends AbstractListenerCardTarot {

    private ContainerMultiTarot container;
    private boolean inHand;
    public ListenerCardTarotMultiDog(ContainerMultiTarot _container, CardTarot _pcarte,boolean _inHand) {
        super(_container, _pcarte);
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
        CardTarot selected_ = getCarteVerif();
        DiscardedCard discard_ = new DiscardedCard();
        discard_.setCard(selected_);
        discard_.setPlace(container.getIndexInGame());
        discard_.setInHand(container.getTakerCardsDog().contient(getCarteVerif()));
        discard_.setLocale(Constants.getLanguage());
        container.getOwner().sendObject(discard_);

    }

}
