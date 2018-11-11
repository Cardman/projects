package cards.gui.events;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.actions.PlayingCardPresident;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.util.NumberMap;

public class ListenerCardPresidentMultiGame extends
        AbstractListenerCardPresident {

    private ContainerMultiPresident container;

    public ListenerCardPresidentMultiGame(ContainerMultiPresident _container,
            CardPresident _card, byte _index) {
        super(_container, _card, _index);
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
        PlayingCardPresident pl_ = new PlayingCardPresident();
        pl_.setPlace(container.getIndexInGame());
        pl_.setPlayedCard(getCarteVerif());
        pl_.setPlayedHand(new HandPresident());
        pl_.setIndex(getIndexVerif());
        pl_.setPass(false);
        String lg_ = container.getOwner().getLanguageKey();
        pl_.setLocale(lg_);
        pl_.setStatus(new NumberMap<Byte, Playing>());
        container.getOwner().sendObject(pl_);
    }
}
