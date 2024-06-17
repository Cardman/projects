package cards.network.threads;

import cards.network.common.before.ReadyCards;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.comparators.ComparatorBoolean;

public final class ServerActLoopCardsReady implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        ReadyCards playerActionBeforeGame_ = Net.importReady(_input);
        if (Net.getGames(_instance).enCoursDePartie()) {
            int noClient_ = playerActionBeforeGame_.getIndex();
            _common.getReadyPlayers().put(noClient_, ComparatorBoolean.of(playerActionBeforeGame_.getContent().isReady()));
            if (_common.allReady()) {
                sendOkToQuit(_instance, _common);
            }
            return;
        }
        int noClient_ = playerActionBeforeGame_.getIndex();
        _common.getReadyPlayers().put(noClient_, ComparatorBoolean.of(playerActionBeforeGame_.getContent().isReady()));
        _common.resend(Net.exportClientReady(playerActionBeforeGame_.getIndex(),playerActionBeforeGame_.getContent().isReady()));
//        for(AbstractSocket so_:_common.getSockets().values()) {
//            NetGroupFrame.trySendString(Net.exportClientReady(playerActionBeforeGame_.getIndex(),playerActionBeforeGame_.isReady()), so_);
//        }
    }

    public static void sendOkToQuit(Net _instance, NetCommon _common) {
        for (byte p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString(Net.exportEnableQuit(), Net.getSocketByPlace(p, _common));
        }
    }
}
