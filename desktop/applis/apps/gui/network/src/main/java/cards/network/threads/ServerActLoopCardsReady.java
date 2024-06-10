package cards.network.threads;

import cards.network.common.before.Ready;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.comparators.ComparatorBoolean;

public final class ServerActLoopCardsReady implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        Ready playerActionBeforeGame_ = Net.importReady(_input);
        if (Net.getGames(_instance).enCoursDePartie()) {
            int noClient_ = playerActionBeforeGame_.getIndex();
            _common.getReadyPlayers().put(noClient_, ComparatorBoolean.of(playerActionBeforeGame_.isReady()));
            if (_common.allReady()) {
                Net.sendOkToQuit(_instance, _common);
            }
            return;
        }
        int noClient_ = playerActionBeforeGame_.getIndex();
        _common.getReadyPlayers().put(noClient_, ComparatorBoolean.of(playerActionBeforeGame_.isReady()));
        _common.resend(Net.exportClientReady(playerActionBeforeGame_.getIndex(),playerActionBeforeGame_.isReady()));
//        for(AbstractSocket so_:_common.getSockets().values()) {
//            NetGroupFrame.trySendString(Net.exportClientReady(playerActionBeforeGame_.getIndex(),playerActionBeforeGame_.isReady()), so_);
//        }
    }
}
