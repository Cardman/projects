package cards.network.threads;

import cards.gui.containers.ContainerSingleTarot;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.tarot.actions.PlayingCardTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.president.GamePresident;
import cards.president.enumerations.CardPresident;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.CustList;
import code.util.IdList;
import code.util.core.NumberUtil;

public abstract class ServerActLoopCardsActedByClient implements IntServerActLoopCards {
    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.setReceivedForPlayer((byte) NumberUtil.parseInt(_input.get(0)), _instance);
        if (!Net.allReceived(_instance)) {
            return;
        }
        Net.initAllReceived(_instance, _common);
        if (Net.getGames(_instance).enCoursDePartieBelote()) {
            loopBelote(_input,_instance,_fct,_common);
        }
        if (Net.getGames(_instance).enCoursDePartiePresident()) {
            loopPresident(_input,_instance,_fct,_common);
        }
        if (Net.getGames(_instance).enCoursDePartieTarot()) {
            loopTarot(_input,_instance,_fct,_common);
        }
    }
    protected static void playingPresidentCard(Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GamePresident game_ = Net.getGames(_instance).partiePresident();
        byte place_ = game_.nextPlayer();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowPlayingPresident allow_ = new AllowPlayingPresident();
            allow_.setEnabledPass(!game_.getProgressingTrick().estVide() && game_.canPass());
            allow_.setStatus(game_.getStatus());
            allow_.setCards(game_.cartesJouables());
            allow_.setReversed(game_.isReversed());
            Net.sendObject(Net.getSocketByPlace(place_, _common),allow_);
            return;
        }
        ThreadUtil.sleep(_fct,800);
//        if (game_.aJoue(place_)) {
//            return;
//        }
        PlayingCardPresident cardDto_ = new PlayingCardPresident();
        cardDto_.setRefreshing(false);
        cardDto_.setPlayedHand(game_.addCardsToCurrentTrick(_instance.getIa().getPresident()));
        cardDto_.setPlace(place_);
        cardDto_.setNextPlayer(game_.nextPlayer());
        cardDto_.setStatus(game_.getLastStatus());
        cardDto_.setPlayedCard(CardPresident.WHITE);
        //cardDto_.setLocale(Constants.getDefaultLanguage());
//        cardDto_.setLocale("");
        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            Net.sendObject(Net.getSocketByPlace(p, _common),cardDto_);
        }
    }
    protected static void playingTarotCard(Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        byte place_ = game_.playerHavingToPlay();
        if (Net.isHumanPlayer(place_, _instance, _common)) {
            AllowPlayingTarot decla_ = new AllowPlayingTarot();
            decla_.setTakerIndex(game_.getPreneur());
            decla_.setCurrentBid(game_.getContrat());
            boolean firstRound_ = game_.premierTourNoMisere();
            decla_.setFirstRoundPlaying(game_.premierTour());
            decla_.setCards(game_.autorise());
            if (firstRound_) {
                decla_.setDiscardedTrumps(ContainerSingleTarot.discarded(game_));
                decla_.setCalledCards(game_.getCalledCards());
//                IdList<Handfuls> handfuls_ = new IdList<Handfuls>(game_.getRegles().getCurrentAllowedHandfuls());
//                decla_.setAllowedHandfuls(new IdList<Handfuls>(handfuls_));
//                decla_.setRequiredTrumps(new IdMap<Handfuls,Integer>(game_.getRegles().getAllowedHandfuls()));
//                decla_.setAllowedMiseres(new IdList<Miseres>(game_.getRegles().getMiseres()));
            } else {
                decla_.setDiscardedTrumps(new HandTarot());
                decla_.setCalledCards(new HandTarot());
//                decla_.setAllowedHandfuls(new IdList<Handfuls>());
//                decla_.setRequiredTrumps(new IdMap<Handfuls,Integer>());
//                decla_.setAllowedMiseres(new IdList<Miseres>());
            }
            Net.sendObject(Net.getSocketByPlace(place_, _common), decla_);
            return;
        }
        ThreadUtil.sleep(_fct,800);
//        if (game_.aJoue(place_)) {
//            return;
//        }
        CardTarot card_ = game_.currentPlayerHasPlayed(_instance.getIa().getTarot());
        PlayingCardTarot cardDto_ = new PlayingCardTarot();
        cardDto_.setRefreshing(false);
        cardDto_.setTakerIndex(game_.getPreneur());
        cardDto_.setPlace(place_);
        cardDto_.setPlayedCard(card_);
        //cardDto_.setLocale(Constants.getDefaultLanguage());
//        cardDto_.setLocale("");
        IdList<Handfuls> annoncesPoignees_ = game_.getAnnoncesPoignees(place_);
        IdList<Miseres> annoncesMiseres_ = game_.getAnnoncesMiseres(place_);
        HandTarot poignee_=game_.getPoignee(place_);
        if (!annoncesPoignees_.isEmpty()) {
            cardDto_.setChoosenHandful(annoncesPoignees_.first());
        } else {
            cardDto_.setChoosenHandful(Handfuls.NO);
        }
        cardDto_.setHandful(poignee_);
        cardDto_.setMiseres(annoncesMiseres_);
        cardDto_.setExcludedTrumps(new HandTarot());
        Net.initAllReceived(_instance, _common);
        for (byte p: Net.activePlayers(_instance, _common)) {
            Net.sendObject(Net.getSocketByPlace(p, _common), cardDto_);
        }
    }

    protected abstract void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common);

    protected abstract void loopPresident(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common);

    protected abstract void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common);
}
