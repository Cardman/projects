package code.mock;

import cards.president.*;
import code.util.*;

public final class MockGamePresident implements IntGamePresident {
    private final CustList<HandPresident> sw = new CustList<HandPresident>();
    private final CustList<HandPresident> cards = new CustList<HandPresident>();
    private int indexSw;
    private int indexCard;
    @Override
    public HandPresident strategieEchange(GamePresident _game, byte _p) {
        return incrSw();
    }

    @Override
    public HandPresident strategieEchangeUser(HandPresident _game) {
        return incrSw();
    }

    @Override
    public HandPresident currentSwitch() {
        return sw.get(indexSw);
    }

    private HandPresident incrSw() {
        HandPresident v_ = sw.get(indexSw);
        indexSw++;
        return v_;
    }

    @Override
    public HandPresident playedCards(GamePresident _game) {
        return incrCard();
    }

    @Override
    public HandPresident playedCardsUser(HandPresident _game) {
        return incrCard();
    }

    @Override
    public HandPresident currentCards() {
        return cards.get(indexCard);
    }

    private HandPresident incrCard() {
        HandPresident v_ = cards.get(indexCard);
        indexCard++;
        return v_;
    }

    public CustList<HandPresident> getSw() {
        return sw;
    }

    public CustList<HandPresident> getCards() {
        return cards;
    }
}
