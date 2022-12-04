package cards.gui.events;

import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.actions.PlayingCardPresident;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.gui.AbsMouseLocation;
import code.util.*;

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
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
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
        pl_.setStatus(new ByteMap< Playing>());
        container.window().sendObject(pl_);
    }
}
