package cards.network.threads;

import cards.belote.GameBelote;
import cards.belote.TricksHandsBelote;
import cards.president.GamePresident;
import cards.president.TricksHandsPresident;
import cards.tarot.GameTarot;
import cards.tarot.TricksHandsTarot;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsActedByClientTricks extends ServerActLoopCardsActedByClientAll {
    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        loopReceive(_input, _instance, _fct, _common);
    }

    @Override
    protected void loopReceive(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        if (!Net.isSameTeam(_instance, _common)) {
            return;
        }
        super.loopReceive(_input, _instance, _fct, _common);
    }

    @Override
    protected void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        byte place_ = (byte) NumberUtil.parseInt(_input.get(0));
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        tricksHands_.tricks(game_);
        NetGroupFrame.trySendString(Net.exportTricksHandsBelote(tricksHands_), Net.getSocketByPlace(place_, _common));
    }

    @Override
    protected void loopPresident(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        byte place_ = (byte) NumberUtil.parseInt(_input.get(0));
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        GamePresident game_ = Net.getGames(_instance).partiePresident();
        tricksHands_.setReversed(game_.isReversed());
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        tricksHands_.setDistributionCopy(game_.getDeal());
        NetGroupFrame.trySendString(Net.exportTricksHandsPresident(tricksHands_), Net.getSocketByPlace(place_, _common));
    }

    @Override
    protected void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        byte place_ = (byte) NumberUtil.parseInt(_input.get(0));
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        tricksHands_.tricks(game_);
        NetGroupFrame.trySendString(Net.exportTricksHandsTarot(tricksHands_), Net.getSocketByPlace(place_, _common));
    }
}
