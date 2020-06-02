package cards.tarot;
import cards.tarot.enumerations.BidTarot;

public class CommonTarotGame {

    static void bidding(BidTarot _bid, byte _taker, GameTarot _game) {
        byte player_ = _game.playerAfter(_game.getDistribution().getDealer());
        while (_game.keepBidding()) {
            if (player_ == _taker) {
                _game.ajouterContrat(_bid, player_);
            } else {
                _game.ajouterContrat(BidTarot.FOLD,player_);
            }
            player_ = _game.playerAfter(player_);
        }
    }
}
