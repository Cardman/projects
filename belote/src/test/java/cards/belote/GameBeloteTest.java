package cards.belote;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import junitparams.JUnitParamsRunner;

import org.junit.runner.RunWith;

import cards.belote.enumerations.BidBelote;
import cards.consts.Suit;

@RunWith(JUnitParamsRunner.class)
public class GameBeloteTest extends CommonBeloteGame{

    void biddingTrumpSuit(BidBelote _bid,Suit _suit) {
        if (!_bid.getCouleurDominante()) {
            fail("Bad bid arg");
        }
        byte playerAfterDealer_ = game.playerAfter(game.getDistribution().getDonneur());
        if (_bid == BidBelote.OTHER_SUIT) {
            for (byte p:game.orderedPlayers(playerAfterDealer_)) {
                BidBeloteSuit contratTmp_ = new BidBeloteSuit();
                contratTmp_.setEnchere(BidBelote.FOLD);
                game.ajouterContrat(contratTmp_,p);
            }
            game.finEncherePremierTour();
        }
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(_bid);
        contratTmp_.setCouleur(_suit);
        game.ajouterContrat(contratTmp_,playerAfterDealer_);
        game.completerDonne();
        assertTrue(game.getContrat().getCouleurDominante());
    }

}
