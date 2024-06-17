package cards.network.threads;

import cards.network.president.actions.PlayingCardPresident;
import cards.president.GamePresident;
import cards.president.enumerations.CardPresident;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsPlayingPresident implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        PlayingCardPresident info_ = Net.importPlayingPresident(_input);
        GamePresident game_ = Net.getGames(_instance).partiePresident();
        if (!info_.isRefreshing()) {
            byte player_ = info_.getPlace();
            if (info_.isPass()) {
                PlayingCardPresident cardDto_ = new PlayingCardPresident();
                cardDto_.setRefreshing(true);
                cardDto_.setPlayedHand(game_.noPlay(_instance.getIa().getPresident()));
                cardDto_.setPlace(player_);
                cardDto_.setNextPlayer(game_.nextPlayer());
                cardDto_.setStatus(game_.getLastStatus());
                cardDto_.setReversed(game_.isReversed());
                cardDto_.setPlayedCard(CardPresident.WHITE);
                //cardDto_.setLocale(Constants.getDefaultLanguage());
//                cardDto_.setLocale("");
                NetGroupFrame.trySendString(Net.exportClientPlayingPresident(cardDto_), Net.getSocketByPlace(player_, _common));
            } else {
                PlayingCardPresident cardDto_ = new PlayingCardPresident();
                cardDto_.setRefreshing(true);
                cardDto_.setPlayedHand(game_.addCardsToCurrentTrick(_instance.getIa().getPresident(), info_.getPlayedCard(), info_.getIndex()));
                cardDto_.setPlace(player_);
                cardDto_.setNextPlayer(game_.nextPlayer());
                cardDto_.setStatus(game_.getLastStatus());
                cardDto_.setReversed(game_.isReversed());
                cardDto_.setPlayedCard(CardPresident.WHITE);
                //cardDto_.setLocale(Constants.getDefaultLanguage());
//                cardDto_.setLocale("");
                NetGroupFrame.trySendString(Net.exportClientPlayingPresident(cardDto_), Net.getSocketByPlace(player_, _common));
            }
            return;
        }
        PlayingCardPresident cardDto_ = new PlayingCardPresident();
        cardDto_.setRefreshing(false);
        cardDto_.setPlayedHand(info_.getPlayedHand());
//            cardDto_.setPlayedHand(game_.getPlayedCards());
        cardDto_.setPlace(info_.getPlace());
        cardDto_.setNextPlayer(game_.nextPlayer());
        cardDto_.setStatus(game_.getLastStatus());
        //cardDto_.setLocale(Constants.getDefaultLanguage());
//            cardDto_.setLocale("");
        cardDto_.setPlayedCard(CardPresident.WHITE);
        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString(Net.exportClientPlayingPresident(cardDto_), Net.getSocketByPlace(p, _common));
        }
    }
}
