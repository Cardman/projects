package cards.gui.events;
import java.awt.event.MouseEvent;

import code.util.consts.Constants;
import cards.gui.containers.ContainerMultiPresident;
import cards.network.president.actions.PlayingCardPresident;
import cards.president.enumerations.CardPresident;

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
        pl_.setIndex(getIndexVerif());
        pl_.setPass(false);
        pl_.setLocale(Constants.getLanguage());
        container.getOwner().sendObject(pl_);
    }
}
