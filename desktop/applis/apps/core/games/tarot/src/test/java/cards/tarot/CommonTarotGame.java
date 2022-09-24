package cards.tarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.tsts.TstsTarot;

public class CommonTarotGame extends EquallableTarotUtil {

    static void bidding(BidTarot _bid, byte _taker, GameTarot _game) {
        byte player_ = _game.playerAfter(_game.getDistribution().getDealer());
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
    }
    static void biddingSix(BidTarot _bid, byte _taker, GameTarot _game) {
        byte player_ = _game.playerAfter(_game.getDistribution().getDealer());
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(TstsTarot.bid(_bid,player_,_taker), player_);
    }
}
