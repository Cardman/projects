package cards.president;

import code.maths.montecarlo.AbstractGenerator;

public final class DefGamePresident implements IntGamePresident {
    @Override
    public HandPresident strategieEchange(GamePresident _game, byte _joueur) {
        return _game.strategieEchange(_joueur);
    }

    @Override
    public HandPresident strategieEchangeUser(HandPresident _hand) {
        return _hand;
    }

    @Override
    public HandPresident currentSwitch() {
        return new HandPresident();
    }

    @Override
    public HandPresident playedCards(GamePresident _game) {
        return _game.playedCards();
    }

    @Override
    public HandPresident playedCardsUser(HandPresident _game) {
        return _game;
    }

    @Override
    public HandPresident currentCards() {
        return new HandPresident();
    }

    @Override
    public DealPresident empiler(long _nb, GamePresident _game, AbstractGenerator _gene) {
        HandPresident stackNext_ = _game.empiler();
        byte dealer_ = _game.getDeal().getDealer();
        DealPresident deal_ = new DealPresident(_nb);
        deal_.donneurSuivant(dealer_,_game.getRules());
        deal_.initDonne(_game.getRules(),_gene,stackNext_);
        return deal_;
    }
}
