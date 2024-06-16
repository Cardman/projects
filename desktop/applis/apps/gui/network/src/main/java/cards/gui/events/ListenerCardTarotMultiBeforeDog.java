package cards.gui.events;

import cards.gui.containers.ContainerMultiTarot;
import cards.network.common.PlayerActionGameType;
import cards.network.tarot.actions.CallAfterDiscardTarot;
import cards.network.threads.Net;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.network.NetCommon;
import code.network.NetGroupFrame;

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
//        String lg_ = container.getOwner().getLanguageKey();
//        if (container.isDiscardCall()) {
//            if (container.getCardsInDog().total()!=container.getRepTarot().getNombreCartesChien()) {
//                return;
//            }
//            container.updateCardsInPanelTarotCallBeforeDogMulti(false);
//            HandTarot cartesAppel_ = new HandTarot();
//            cartesAppel_.ajouter(getCard());
//            CalledCards calledCards_ = new CalledCards();
//            calledCards_.setCalledCards(cartesAppel_);
//            calledCards_.setDiscarding(true);
//            calledCards_.setLocale(lg_);
//            container.window().sendObject(calledCards_);
//        }
        container.updateCardsInPanelTarotCallBeforeDogMulti(false);
        container.setChienMulti(false);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(getCard());
        if (NetCommon.QUICK) {
            NetGroupFrame.trySendString(Net.exportDiscardCallBefore(cartesAppel_),container.getContainerMultiContent().window().getSocket());
            return;
        }
        CallAfterDiscardTarot calledCards_ = new CallAfterDiscardTarot(PlayerActionGameType.SIMPLE);
        calledCards_.setCalledCards(cartesAppel_);
//        calledCards_.setLocale(lg_);
        container.getContainerMultiContent().window().sendObject(calledCards_);
    }
}
