package aiki.network;

import aiki.network.stream.QuitAiki;
import cards.network.threads.Net;
import code.gui.initialize.AbstractSocket;
import code.network.Exiting;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ServerActLoopAikiQuitAiki implements IntServerActLoopAiki {
    @Override
    public void loop(CustList<String> _input, NetAiki _instance, NetCommon _common) {
        QuitAiki bye_ = NetAiki.importQuitAiki(_input);
        Exiting forcedBye_ = new Exiting();
        forcedBye_.setForced(false);
        forcedBye_.setServer(true);
        forcedBye_.setTooManyPlayers(false);
        AbstractSocket socket_;
        socket_ = _common.getSockets().getVal((int) IndexConstants.FIRST_INDEX);
        _common.getConnectionsServer().removeKey((int) IndexConstants.FIRST_INDEX);
        _common.getReadyPlayers().removeKey((int) IndexConstants.FIRST_INDEX);
        _common.getPlacesPlayers().removeKey((int) IndexConstants.FIRST_INDEX);
        if (bye_.getPlace() == IndexConstants.FIRST_INDEX) {
            forcedBye_.setClosing(bye_.isClosing());
        } else {
            forcedBye_.setClosing(false);
        }
        if (socket_ != null) {
            NetGroupFrame.trySendString(Net.exportExiting(forcedBye_), socket_);
        }
        socket_ = _common.getSockets().getVal((int) IndexConstants.SECOND_INDEX);
        _common.getConnectionsServer().removeKey((int) IndexConstants.SECOND_INDEX);
        _common.getReadyPlayers().removeKey((int) IndexConstants.SECOND_INDEX);
        _common.getPlacesPlayers().removeKey((int) IndexConstants.SECOND_INDEX);
        if (bye_.getPlace() == IndexConstants.SECOND_INDEX) {
            forcedBye_.setClosing(bye_.isClosing());
        } else {
            forcedBye_.setClosing(false);
        }
        if (socket_ != null) {
            NetGroupFrame.trySendString(Net.exportExiting(forcedBye_), socket_);
        }
        _common.getSockets().clear();
    }
}
