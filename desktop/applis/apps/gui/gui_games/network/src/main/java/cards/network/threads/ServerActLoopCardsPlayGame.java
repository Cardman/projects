package cards.network.threads;

import cards.belote.DealBelote;
import cards.consts.Suit;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.president.displaying.DealtHandPresident;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.president.DealPresident;
import cards.president.RulesPresident;
import cards.tarot.DealTarot;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsPlayGame implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Net.initAllPresent(_instance,_common);
        Net.initAllReceived(_instance,_common);
        if (Net.getGames(_instance).enCoursDePartieBelote()) {
            Net.setProgressingGame(true, _instance);
            DealBelote deal_ = Net.getGames(_instance).partieBelote().getDistribution();
            DealtHandBelote hand_ = new DealtHandBelote();
            hand_.setDeck(deal_.derniereMain());
            hand_.setDealer(Net.getGames(_instance).partieBelote().playerAfter(deal_.getDealer()));
            hand_.setAllowedBids(Net.getGames(_instance).partieBelote().getGameBeloteBid().allowedBids());
            for (int i:Net.activePlayers(_instance, _common)) {
                hand_.setCards(deal_.hand(i));
                NetGroupFrame.trySendString(Net.exportDealtHandBelote(hand_), Net.getSocketByPlace(i, _common));
            }
        }
        if (Net.getGames(_instance).enCoursDePartiePresident()) {
            Net.setProgressingGame(true, _instance);
            int nbSuits_ = Suit.couleursOrdinaires().size();
            RulesPresident rules_ = Net.getGames(_instance).partiePresident().getRules();
            int nbStacks_ = rules_.getNbStacks();
            DealPresident deal_ = Net.getGames(_instance).partiePresident().getDeal();
            DealtHandPresident hand_ = new DealtHandPresident();
            hand_.setDealer(Net.getGames(_instance).partiePresident().getDeal().getDealer());
            hand_.setMaxCards(NumberUtil.min(nbSuits_ * nbStacks_, rules_.getNbMaxCardsPerPlayer()));
            hand_.setStatus(Net.getGames(_instance).partiePresident().getLastStatus());
            for (int i:Net.activePlayers(_instance, _common)) {
                hand_.setCards(deal_.hand(i));
                NetGroupFrame.trySendString(Net.exportDealtHandPresident(hand_), Net.getSocketByPlace(i, _common));
            }
        }
        if (Net.getGames(_instance).enCoursDePartieTarot()) {
            Net.setProgressingGame(true, _instance);
            DealTarot deal_ = Net.getGames(_instance).partieTarot().getDistribution();
            DealtHandTarot hand_ = new DealtHandTarot();
            hand_.setDog(deal_.derniereMain());
            hand_.setDealer(Net.getGames(_instance).partieTarot().playerAfter(deal_.getDealer()));
            hand_.setAllowedBids(Net.getGames(_instance).partieTarot().allowedBids());
            for (int i:Net.activePlayers(_instance, _common)) {
                hand_.setCards(deal_.hand(i));
                NetGroupFrame.trySendString(Net.exportDealtHandTarot(hand_), Net.getSocketByPlace(i, _common));
            }
        }
    }
}
