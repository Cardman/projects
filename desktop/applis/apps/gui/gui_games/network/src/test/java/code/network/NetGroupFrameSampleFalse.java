package code.network;

import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractSocket;
import code.mock.MockSocket;
import code.mock.MockThreadFactory;
import code.util.core.BoolVal;

public final class NetGroupFrameSampleFalse extends NetGroupFrame {
    public NetGroupFrameSampleFalse(String _lg, AbstractProgramInfos _list) {
        super(_list);
    }

    @Override
    public void changeLanguage(String _language) {
        getFrames().setLanguage(_language);
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public void quit() {
        setVisible(false);
    }

    @Override
    public AbstractSocket initIndexInGame(AbstractSocket _socket) {
        getThreadFactory().newStartedThread(new BasicClientFalse(_socket));
        return _socket;
    }

    @Override
    public void gearClient(AbstractSocket _newSocket) {
        int nb_ = getSockets().getSockets().size();
        getSockets().getSockets().put(nb_, _newSocket);
        BasicServer serv_ = new BasicServerSample(_newSocket, this);
        MockSocket ms_ = (MockSocket) serv_.getSocket();
        ms_.setLoopTrue();
        ms_.getInstr().add("_");
        MockSocket mc_ = (MockSocket) getSocket();
        mc_.getInstr().add("_");
        mc_.setLoopTrue();
        ((MockThreadFactory)getThreadFactory()).getAllThreads().last().join();
        trySendString("_",_newSocket);
        serv_.run();
        getSockets().getConnectionsServer().put(nb_ ,serv_);
        getSockets().getReadyPlayers().put(nb_ , BoolVal.FALSE);
        getSockets().getPlacesPlayers().put(nb_ ,(byte)(nb_));
        trySendString("_",_newSocket);
    }
}
