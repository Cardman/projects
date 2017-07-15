package cards.belote;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import junitparams.JUnitParamsRunner;

import org.junit.runner.RunWith;

import cards.belote.enumerations.BidBelote;

@RunWith(JUnitParamsRunner.class)
public class GameBeloteWithoutTrumpSuit extends CommonBeloteGame {

    void bidding(BidBelote _bid) {
        if (_bid.getCouleurDominante()) {
            fail("Bad bid arg");
        }
        byte playerAfterDealer_ = game.playerAfter(game.getDistribution().getDonneur());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setEnchere(_bid);
        game.ajouterContrat(contratTmp_,playerAfterDealer_);
//        game.setPreneur(game.playerAfter(game.getDistribution().getDonneur()));
//        game.setContrat(contrat_tmp);
        game.completerDonne();
        assertTrue(!game.getContrat().getCouleurDominante());
    }
}
