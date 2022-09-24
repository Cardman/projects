package cards.belote;
import cards.belote.enumerations.BidBelote;
import cards.consts.Suit;

public class GameBeloteWithTrumpSuit extends EquallableBeloteUtil {

    void biddingTrumpSuit(GameBelote _game, Suit _suit) {
        byte playerAfterDealer_ = _game.playerAfter(_game.getDistribution().getDealer());
        addBids(_game,playerAfterDealer_);
//        for (byte p: _game.orderedPlayers(playerAfterDealer_)) {
//            addBid(_game, p);
//        }
        _game.finEncherePremierTour();
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.OTHER_SUIT);
        contratTmp_.setSuit(_suit);
        _game.ajouterContrat(contratTmp_,playerAfterDealer_);
        _game.completerDonne();
        assertTrue(_game.getBid().getCouleurDominante());
    }

    private void addBids(GameBelote _game, byte _f) {
        addBid(_game,_f);
        byte n_ = _game.playerAfter(_f);
        addBid(_game,n_);
        byte n1_ = _game.playerAfter(n_);
        addBid(_game,n1_);
        byte n2_ = _game.playerAfter(n1_);
        addBid(_game,n2_);
    }
    private void addBid(GameBelote _game, byte _p) {
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.FOLD);
        _game.ajouterContrat(contratTmp_, _p);
    }

    void biddingTrumpSuitFirst(GameBelote _game, Suit _suit) {
        byte playerAfterDealer_ = _game.playerAfter(_game.getDistribution().getDealer());
        BidBeloteSuit contratTmp_ = new BidBeloteSuit();
        contratTmp_.setBid(BidBelote.SUIT);
        contratTmp_.setSuit(_suit);
        _game.ajouterContrat(contratTmp_,playerAfterDealer_);
        _game.completerDonne();
        assertTrue(_game.getBid().getCouleurDominante());
    }
}
