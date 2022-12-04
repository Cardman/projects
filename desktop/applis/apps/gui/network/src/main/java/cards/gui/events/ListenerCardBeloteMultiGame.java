package cards.gui.events;

import cards.belote.DeclareHandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.actions.PlayingCardBelote;
import code.gui.AbsMouseLocation;

public class ListenerCardBeloteMultiGame extends AbstractListenerCardBelote {

    private ContainerMultiBelote container;

    public ListenerCardBeloteMultiGame(ContainerMultiBelote _container,
            CardBelote _pcarte) {
        super(_container, _pcarte);
        container = _container;
    }

    @Override
    protected boolean canListen() {
        return container.isCanPlay();
    }

    @Override
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }

    @Override
    protected void verifierRegles() {
        container.setCanPlay(false);
        PlayingCardBelote pl_ = new PlayingCardBelote();
        pl_.setPlace(container.getIndexInGame());
        pl_.setDeclaring(container.isAnnonceBelote());
        pl_.setDeclaringBeloteRebelote(container.isAnnonceBeloteRebelote());
        pl_.setPlayedCard(getCarteVerif());
        pl_.setDeclare(new DeclareHandBelote());
        String lg_ = container.getOwner().getLanguageKey();
        pl_.setLocale(lg_);
        container.window().sendObject(pl_);
    }

}
