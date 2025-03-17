package cards.network.threads;

import cards.belote.TricksHandsBelote;
import cards.president.GamePresident;
import cards.president.TricksHandsPresident;
import cards.tarot.TricksHandsTarot;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsActedByClientTricks extends ServerActLoopCardsActedByClientAll {
    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        loopReceiveTeams(_input, _instance, _fct, _common);
    }

    @Override
    protected void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.tricks(Net.getGames(_instance).partieBelote());
        NetGroupFrame.trySendString(Net.exportTricksHandsBelote(tricksHands_), Net.getSocketByPlace(NumberUtil.parseInt(_input.get(0)), _common));
    }

    @Override
    protected void loopPresident(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        GamePresident game_ = Net.getGames(_instance).partiePresident();
        tricksHands_.setReversed(game_.isReversed());
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        tricksHands_.setDistributionCopy(game_.getDeal());
        NetGroupFrame.trySendString(Net.exportTricksHandsPresident(tricksHands_), Net.getSocketByPlace(NumberUtil.parseInt(_input.get(0)), _common));
    }

    @Override
    protected void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.tricks(Net.getGames(_instance).partieTarot());
        NetGroupFrame.trySendString(Net.exportTricksHandsTarot(tricksHands_), Net.getSocketByPlace(NumberUtil.parseInt(_input.get(0)), _common));
    }
}
