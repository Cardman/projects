package cards.network.threads;

import cards.belote.GameBelote;
import cards.belote.enumerations.CardBelote;
import cards.network.belote.actions.PlayingCardBelote;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsPlayingBelote implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        PlayingCardBelote info_ = Net.importPlayingBelote(_input);
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        if (!info_.isRefreshing()) {
            CardBelote card_ = info_.getPlayedCard();
            if(info_.isDeclaring()) {
                game_.annoncer();
            }
            CardBelote played_ = _instance.getIa().getBelote().strategieJeuCarteUniqueUser(card_);
            if(info_.isDeclaringBeloteRebelote()) {
                game_.setAnnoncesBeloteRebelote(played_);
            }
            game_.ajouterUneCarteDansPliEnCoursJoue(played_);
            PlayingCardBelote ref_ = new PlayingCardBelote();
            ref_.setRefreshing(true);
            ref_.setPlayedCard(played_);
            ref_.setDeclaringBeloteRebelote(info_.isDeclaringBeloteRebelote());
            ref_.setDeclaring(info_.isDeclaring());
            ref_.setPlace(info_.getPlace());
            ref_.setDeclare(game_.getAnnonce(info_.getPlace()));
            //ref_.setLocale(Constants.getDefaultLanguage());
//            ref_.setLocale("");
            NetGroupFrame.trySendString(Net.exportClientPlayingBelote(ref_), Net.getSocketByPlace(info_.getPlace(), _common));
            return;
        }
        PlayingCardBelote p_ = new PlayingCardBelote();
        p_.setRefreshing(false);
        p_.setTakerIndex(game_.getPreneur());
        p_.setPlace(info_.getPlace());
        p_.setPlayedCard(info_.getPlayedCard());
        p_.setDeclaringBeloteRebelote(info_.isDeclaringBeloteRebelote());
        p_.setDeclaring(info_.isDeclaring());
        p_.setDeclare(info_.getDeclare());
        //p_.setLocale(Constants.getDefaultLanguage());
//            p_.setLocale("");
        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString(Net.exportClientPlayingBelote(p_), Net.getSocketByPlace(p, _common));
        }
    }
}
