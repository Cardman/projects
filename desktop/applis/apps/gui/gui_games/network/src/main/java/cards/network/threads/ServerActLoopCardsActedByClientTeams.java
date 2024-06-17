package cards.network.threads;

import cards.belote.GameBelote;
import cards.tarot.GameTarot;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsActedByClientTeams extends ServerActLoopCardsActedByClientFull {
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
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        NetGroupFrame.trySendString(Net.exportTeams(game_.playersBelongingToSameTeam()), Net.getSocketByPlace(place_, _common));
    }

    @Override
    protected void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        byte place_ = (byte) NumberUtil.parseInt(_input.get(0));
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        NetGroupFrame.trySendString(Net.exportTeams(game_.getTeamsRelation().teams()), Net.getSocketByPlace(place_, _common));
    }
}
