package cards.network.threads;

import cards.network.common.before.ChoosenPlace;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsChosenPlace implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        ChoosenPlace ch_ = Net.importChosenPlace(_input);
        int noClient_ = ch_.getIndex();
        _common.getPlacesPlayers().put(noClient_, (byte) ch_.getPlace());
        String str_ = Net.exportClientChosenPlace(ch_.getIndex(), ch_.getPlace(), _common.getPlacesPlayers());
        _common.resend(str_);
    }
}
