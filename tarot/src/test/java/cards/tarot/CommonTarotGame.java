package cards.tarot;
import cards.tarot.enumerations.BidTarot;

public class CommonTarotGame {

    GameTarot game;

    void bidding(BidTarot _bid,byte _taker) {
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        while (game.keepBidding()) {
            if (player_ == _taker) {
                game.ajouterContrat(_bid, player_);
            } else {
                game.ajouterContrat(BidTarot.FOLD,player_);
            }
            player_ = game.playerAfter(player_);
        }
    }
}
