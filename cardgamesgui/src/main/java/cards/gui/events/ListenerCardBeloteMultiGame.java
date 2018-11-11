package cards.gui.events;
import java.awt.event.MouseEvent;

import cards.belote.DeclareHandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.containers.ContainerMultiBelote;
import cards.network.belote.actions.PlayingCardBelote;

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
    protected boolean playCardExited(MouseEvent _event) {
        return _event.getPoint().y < 0;
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
        container.getOwner().sendObject(pl_);
    }

}
