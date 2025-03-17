package aiki.network;

import aiki.network.stream.QuitAiki;
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
        socket_ = _common.getSockets().getVal(IndexConstants.FIRST_INDEX);
        _common.getConnectionsServer().removeKey(IndexConstants.FIRST_INDEX);
        _common.getReadyPlayers().removeKey(IndexConstants.FIRST_INDEX);
        _common.getPlacesPlayers().removeKey(IndexConstants.FIRST_INDEX);
        if (bye_.getPlace() == IndexConstants.FIRST_INDEX) {
            forcedBye_.setClosing(bye_.getContent().isClosing());
        } else {
            forcedBye_.setClosing(false);
        }
        NetGroupFrame.trySendString(NetCommon.exportExiting(forcedBye_), socket_);
//        if (socket_ != null) {
//            NetGroupFrame.trySendString(NetCommon.exportExiting(forcedBye_), socket_);
//        }
        socket_ = _common.getSockets().getVal(IndexConstants.SECOND_INDEX);
        _common.getConnectionsServer().removeKey(IndexConstants.SECOND_INDEX);
        _common.getReadyPlayers().removeKey(IndexConstants.SECOND_INDEX);
        _common.getPlacesPlayers().removeKey(IndexConstants.SECOND_INDEX);
        if (bye_.getPlace() == IndexConstants.SECOND_INDEX) {
            forcedBye_.setClosing(bye_.getContent().isClosing());
        } else {
            forcedBye_.setClosing(false);
        }
        NetGroupFrame.trySendString(NetCommon.exportExiting(forcedBye_), socket_);
//        if (socket_ != null) {
//            NetGroupFrame.trySendString(NetCommon.exportExiting(forcedBye_), socket_);
//        }
        _common.getSockets().clear();
    }
}
