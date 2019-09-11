package cards.gui.events;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.CalledCards;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;

public class ListenerCardTarotMultiBeforeDog extends AbstractListenerCardTarot {

    private ContainerMultiTarot container;

    public ListenerCardTarotMultiBeforeDog(ContainerMultiTarot _container,CardTarot _card) {
        super(_container,_card);
        container = _container;
    }
    @Override
    protected boolean playCardExited(MouseEvent _event) {
        return _event.getY() < 0;
    }
    @Override
    protected boolean canListen() {
        return container.isCanCall();
    }
    @Override
    protected void verifierRegles(){
        String lg_ = container.getOwner().getLanguageKey();
        if (container.isDiscardCall()) {
            if (container.getCardsInDog().total()!=container.getRepTarot().getNombreCartesChien()) {
                return;
            }
            container.setCanCall(false);
            HandTarot cartesAppel_ = new HandTarot();
            cartesAppel_.ajouter(getCarteVerif());
            CalledCards calledCards_ = new CalledCards();
            calledCards_.setCalledCards(cartesAppel_);
            calledCards_.setDiscarding(true);
            calledCards_.setLocale(lg_);
            container.getOwner().sendObject(calledCards_);
        }
        container.setCanCall(false);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(getCarteVerif());
        CalledCards calledCards_ = new CalledCards();
        calledCards_.setCalledCards(cartesAppel_);
        calledCards_.setLocale(lg_);
        container.getOwner().sendObject(calledCards_);
    }
}
