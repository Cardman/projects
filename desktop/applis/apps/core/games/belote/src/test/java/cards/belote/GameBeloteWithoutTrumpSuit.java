package cards.belote;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import cards.belote.enumerations.BidBelote;

public class GameBeloteWithoutTrumpSuit {

    void bidding(GameBelote _game,BidBelote _bid) {
        if (_bid.getCouleurDominante()) {
            fail("Bad bid arg");
        }
        byte playerAfterDealer_ = _game.playerAfter(_game.getDistribution().getDealer());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(_bid);
        _game.ajouterContrat(contratTmp_,playerAfterDealer_);
        _game.completerDonne();
        assertTrue(!_game.getContrat().getCouleurDominante());
    }
}
