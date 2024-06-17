package code.network;

import aiki.network.NetAikiRetrievedInfos;
import cards.network.threads.Net;
import cards.network.threads.NetRetrievedInfos;
import code.gui.initialize.AbstractSocket;

/**Thread safe class*/
public final class BasicClient extends BasicClientAbs {

    private final WindowNetWork windowNetWork;
    public BasicClient(AbstractSocket _socket, WindowNetWork _window) {
        super(_socket);
        windowNetWork = _window;
    }

//    @Override
//    public void run() {
//        AbstractBufferedReader inputSock_ = getSocket().getInput();
//        while (true) {
//            //tourne toujours
//            String input_ = inputSock_.readLine();
//            if (input_ == null) {
//                return;
//            }
//            //on peut traiter les "timeout"
//            if (!iterate(getSocket(),getNet(),input_)) {
//                return;
//            }
//            Document doc_ = getNet().getDoc(input_);
//            if (doc_ == null) {
//                continue;
//            }
//            Exiting exiting_ = getNet().getExiting(doc_);
//            if (exiting_ != null) {
//                getNet().getFrames().getCompoFactory().invokeNow(new Quitting(exiting_, getNet(), getSocket()));
//                return;
//            }
//            getNet().getFrames().getCompoFactory().invokeNow(new LoopClient(getNet(),doc_, getSocket()));
//        }
//    }

    @Override
    protected boolean iterate(AbstractSocket _socket, String _input) {
        return iterate(_socket,windowNetWork,_input);
    }

    public static boolean iterate(AbstractSocket _socket, WindowNetWork _window, String _input) {
        if (_window.isCards()) {
            NetRetrievedInfos net_ = Net.netRetrievedInfos(_input, _window.getNet());
            if (net_.getIndexAct() < 0) {
                _window.getFrames().getCompoFactory().invokeNow(new Quitting(NetCommon.importExiting(net_.getParts()), _window, _socket));
                return false;
            }
            _window.getFrames().getCompoFactory().invokeNow(new LoopClient(_window, _socket, net_, null));
            return true;
        }
        NetAikiRetrievedInfos net_ = new NetAikiRetrievedInfos(_input);
        if (net_.getIndexAct() < 0) {
            _window.getFrames().getCompoFactory().invokeNow(new Quitting(NetCommon.importExiting(net_.getParts()), _window, _socket));
            return false;
        }
        _window.getFrames().getCompoFactory().invokeNow(new LoopClient(_window, _socket, null, net_));
        return true;
    }
}
