package cards.network.threads;

import cards.network.tarot.actions.PlayingCardTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.IdList;

public final class ServerActLoopCardsPlayingTarot implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        PlayingCardTarot info_ = Net.importPlayingTarot(_input);
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        if (!info_.isRefreshing()) {
            processPlayingTarot(info_, _instance, _common);
            return;
        }
        PlayingCardTarot p_ = new PlayingCardTarot();
        p_.setRefreshing(false);
        p_.setTakerIndex(game_.getPreneur());
        p_.setPlace(info_.getPlace());
        p_.setPlayedCard(info_.getPlayedCard());
        p_.setChoosenHandful(info_.getChoosenHandful());
        p_.setHandful(info_.getHandful());
        p_.setMiseres(info_.getMiseres());
        p_.setCalledCard(info_.isCalledCard());
        p_.setFirstRound(info_.isFirstRound());
        p_.setExcludedTrumps(new HandTarot());
        //p_.setLocale(Constants.getDefaultLanguage());
//            p_.setLocale("");
        Net.initAllReceived(_instance, _common);
        for (int p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString(Net.exportClientPlayingTarot(p_), Net.getSocketByPlace(p, _common));
        }
    }

    private static void processPlayingTarot(PlayingCardTarot _readObject, Net _instance, NetCommon _common) {
        CardTarot card_ = _readObject.getPlayedCard();
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        CardTarot played_ = _instance.getIa().getTarot().changerConfianceJeuCarteUniqueUser(card_);
        game_.ajouterUneCarteDansPliEnCours(played_);
        PlayingCardTarot ref_ = new PlayingCardTarot();
        if(game_.premierTourNoMisere()) {
            Handfuls ch_ = _readObject.getChoosenHandful();
            if (ch_ != Handfuls.NO) {
                IdList<Handfuls> handfuls_ = new IdList<Handfuls>();
                handfuls_.add(ch_);
                game_.setAnnoncesPoignees(_instance.getIa().getTarot().handful(handfuls_));
                game_.ajouterPoignee(_instance.getIa().getTarot().handfulCard(_readObject.getHandful()));
            }
            IdList<Miseres> declaredMiseres_ = new IdList<Miseres>();
            for (Miseres m: _readObject.getMiseres()) {
                if (!game_.getAnnoncesMiseresPossibles(_readObject.getPlace()).containsObj(m)) {
                    continue;
                }
                declaredMiseres_.add(m);
            }
            game_.setAnnoncesMiseres(_instance.getIa().getTarot().misere(declaredMiseres_));
            ref_.setMiseres(declaredMiseres_);
            ref_.setFirstRound(true);
        } else {
            ref_.setMiseres(new IdList<Miseres>());
            ref_.setFirstRound(false);
        }
        ref_.setRefreshing(true);
        ref_.setExcludedTrumps(new HandTarot());
        ref_.setPlayedCard(played_);
        ref_.setPlace(_readObject.getPlace());
        if (!game_.getAnnoncesPoignees(_readObject.getPlace()).isEmpty()) {
            ref_.setChoosenHandful(game_.getAnnoncesPoignees(_readObject.getPlace()).first());
        } else {
            ref_.setChoosenHandful(Handfuls.NO);
        }
        ref_.setHandful(game_.getPoignee(_readObject.getPlace()));
        //ref_.setLocale(Constants.getDefaultLanguage());
//        ref_.setLocale("");
        ref_.setCalledCard(game_.getCarteAppelee().contient(played_));
        NetGroupFrame.trySendString(Net.exportClientPlayingTarot(ref_), Net.getSocketByPlace(_readObject.getPlace(), _common));
    }

}
