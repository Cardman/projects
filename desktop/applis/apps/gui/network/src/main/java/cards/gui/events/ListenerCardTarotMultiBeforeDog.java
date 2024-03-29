package cards.gui.events;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.tarot.actions.CalledCards;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;

public class ListenerCardTarotMultiBeforeDog extends AbstractListenerCard<CardTarot> {

    private final ContainerMultiTarot container;

    public ListenerCardTarotMultiBeforeDog(ContainerMultiTarot _container,CardTarot _card) {
        super(_container,_card);
        container = _container;
    }
//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        return _event.getYcoord() < 0;
//    }

    @Override
    protected void verifierRegles(){
        String lg_ = container.getOwner().getLanguageKey();
        if (container.isDiscardCall()) {
            if (container.getCardsInDog().total()!=container.getRepTarot().getNombreCartesChien()) {
                return;
            }
            container.updateCardsInPanelTarotCallBeforeDogMulti(false);
            HandTarot cartesAppel_ = new HandTarot();
            cartesAppel_.ajouter(getCard());
            CalledCards calledCards_ = new CalledCards();
            calledCards_.setCalledCards(cartesAppel_);
            calledCards_.setDiscarding(true);
            calledCards_.setLocale(lg_);
            container.window().sendObject(calledCards_);
        }
        container.updateCardsInPanelTarotCallBeforeDogMulti(false);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(getCard());
        CalledCards calledCards_ = new CalledCards();
        calledCards_.setCalledCards(cartesAppel_);
        calledCards_.setLocale(lg_);
        container.window().sendObject(calledCards_);
    }
}
