package cards.gui.events;

import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.actions.PlayingCardPresident;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.util.IntMap;

public class ListenerCardPresidentMultiGame extends
        AbstractListenerCard<CardPresident> {

    private final ContainerMultiPresident container;

    public ListenerCardPresidentMultiGame(ContainerMultiPresident _container,
            CardPresident _card, int _index) {
        super(_container, _card, _index);
        container = _container;
    }

//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        return _event.getYcoord() < 0;
//    }

    @Override
    protected void verifierRegles() {
        container.updateCardsInPanelPresidentMulti(false);
        PlayingCardPresident pl_ = new PlayingCardPresident();
        pl_.setRefreshing(false);
        pl_.setPlace(container.getContainerMultiContent().getIndexInGame());
        pl_.setPlayedCard(getCard());
        pl_.setPlayedHand(new HandPresident());
        pl_.setIndex(getIndex());
        pl_.setPass(false);
//        String lg_ = container.getOwner().getLanguageKey();
//        pl_.setLocale(lg_);
        pl_.setStatus(new IntMap< Playing>());
        container.getContainerMultiContent().window().sendObject(pl_);
    }
}
