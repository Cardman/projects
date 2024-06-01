package cards.gui.events;

import cards.belote.DeclareHandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.actions.PlayingCardBelote;

public class ListenerCardBeloteMultiGame extends AbstractListenerCard<CardBelote> {

    private final ContainerMultiBelote container;

    public ListenerCardBeloteMultiGame(ContainerMultiBelote _container,
            CardBelote _pcarte) {
        super(_container, _pcarte);
        container = _container;
    }

//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        return _event.getYcoord() < 0;
//    }

    @Override
    protected void verifierRegles() {
        container.updateCardsInPanelBeloteMulti(false);
        container.pack();
        PlayingCardBelote pl_ = new PlayingCardBelote();
        pl_.setPlace(container.getIndexInGame());
        pl_.setDeclaring(container.getBeloteDeclare().isSelected());
        pl_.setDeclaringBeloteRebelote(ListenerCardBeloteSingleGame.belReb(container.getBelReb(),container,getCard()));
//        pl_.setDeclaringBeloteRebelote(container.getBeloteRebelote().isSelected());
        pl_.setPlayedCard(getCard());
        pl_.setDeclare(new DeclareHandBelote());
//        String lg_ = container.getOwner().getLanguageKey();
//        pl_.setLocale(lg_);
        container.window().sendObject(pl_);
    }

}
