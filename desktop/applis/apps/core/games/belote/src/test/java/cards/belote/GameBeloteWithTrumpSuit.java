package cards.belote;
import cards.belote.enumerations.BidBelote;
import cards.consts.Suit;

public class GameBeloteWithTrumpSuit extends EquallableBeloteUtil {

    void biddingTrumpSuit(GameBelote _game,BidBelote _bid,Suit _suit) {
        assertTrue("Bad bid arg",_bid.getCouleurDominante());
        byte playerAfterDealer_ = _game.playerAfter(_game.getDistribution().getDealer());
        if (_bid == BidBelote.OTHER_SUIT) {
            for (byte p: _game.orderedPlayers(playerAfterDealer_)) {
                BidBeloteSuit contratTmp_ = new BidBeloteSuit();
                contratTmp_.setEnchere(BidBelote.FOLD);
                _game.ajouterContrat(contratTmp_,p);
            }
            _game.finEncherePremierTour();
        }
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(_bid);
        contratTmp_.setCouleur(_suit);
        _game.ajouterContrat(contratTmp_,playerAfterDealer_);
        _game.completerDonne();
        assertTrue(_game.getContrat().getCouleurDominante());
    }
}
