package cards.tarot;
import cards.tarot.enumerations.BidTarot;

public abstract class CommonTarotGame extends EquallableTarotUtil {

    static void bidding0(BidTarot _bid, int _taker, GameTarot _game) {
        int player_ = _game.playerAfter(_game.getDistribution().getDealer());
        _game.ajouterContrat(_bid);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
    }

    static void bidding1(BidTarot _bid, int _taker, GameTarot _game) {
        int player_ = _game.playerAfter(_game.getDistribution().getDealer());
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(_bid);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
    }

    static void bidding4(BidTarot _bid, int _taker, GameTarot _game) {
        int player_ = _game.playerAfter(_game.getDistribution().getDealer());
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(_bid);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
    }

    static void bidding5(BidTarot _bid, int _taker, GameTarot _game) {
        int player_ = _game.playerAfter(_game.getDistribution().getDealer());
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(_bid);
    }
    static void biddingSix(BidTarot _bid, int _taker, GameTarot _game) {
        int player_ = _game.playerAfter(_game.getDistribution().getDealer());
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(_bid);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
        player_ = _game.playerAfter(player_);
        _game.ajouterContrat(BidTarot.FOLD);
    }
}
