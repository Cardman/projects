package cards.belote;
import cards.belote.enumerations.BidBelote;

public class GameBeloteWithoutTrumpSuit extends EquallableBeloteUtil {

    void bidding(GameBelote _game,BidBelote _bid) {
        assertFalse("Bad bid arg",_bid.getCouleurDominante());
        byte playerAfterDealer_ = _game.playerAfter(_game.getDistribution().getDealer());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(_bid);
        _game.ajouterContrat(contratTmp_,playerAfterDealer_);
        _game.completerDonne();
        assertTrue(!_game.getBid().getCouleurDominante());
    }
}
