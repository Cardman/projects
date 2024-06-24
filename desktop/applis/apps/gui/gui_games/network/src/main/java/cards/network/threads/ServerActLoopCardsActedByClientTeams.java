package cards.network.threads;

import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class ServerActLoopCardsActedByClientTeams extends ServerActLoopCardsActedByClientFull {
    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        loopReceiveTeams(_input, _instance, _fct, _common);
    }

    @Override
    protected void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        NetGroupFrame.trySendString(Net.exportTeams(Net.getGames(_instance).partieBelote().playersBelongingToSameTeam()), Net.getSocketByPlace((byte) NumberUtil.parseInt(_input.get(0)), _common));
    }

    @Override
    protected void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        NetGroupFrame.trySendString(Net.exportTeams(Net.getGames(_instance).partieTarot().getTeamsRelation().teams()), Net.getSocketByPlace((byte) NumberUtil.parseInt(_input.get(0)), _common));
    }
}
