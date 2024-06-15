package code.network;

import cards.network.threads.Net;
import code.gui.initialize.AbstractSocket;
import code.sml.Document;

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
        Document doc_;
        if (Net.QUICK) {
            doc_ = null;
        } else {
            doc_ = _window.getDoc(_input);
        }
        return iterate(_socket, _window, _input, doc_);
    }

    public static boolean iterate(AbstractSocket _socket, WindowNetWork _window, String _input, Document _doc) {
        if (_doc == null) {
            if (Net.QUICK) {
                _window.getFrames().getCompoFactory().invokeNow(new LoopClient(_window, _input, null, _socket));
                return true;
            }
            return true;
        }
        Exiting exiting_ = _window.getExiting(_doc);
        if (exiting_ != null) {
            _window.getFrames().getCompoFactory().invokeNow(new Quitting(exiting_, _window, _socket));
            return false;
        }
        _window.getFrames().getCompoFactory().invokeNow(new LoopClient(_window, _input, _doc, _socket));
        return true;
    }
}
