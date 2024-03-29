package code.network;

import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;
import code.sml.Document;

/**Thread safe class*/
public final class BasicClient extends SendReceive {

    public BasicClient(AbstractSocket _socket, NetGroupFrame _window) {
        super(_socket,_window);
    }

    @Override
    public void run() {
        AbstractBufferedReader inputSock_ = getSocket().getInput();
        while (true) {
            //tourne toujours
            String input_ = inputSock_.readLine();
            if (input_ == null) {
                return;
            }
            //on peut traiter les "timeout"
            Document doc_ = getNet().getDoc(input_);
            if (doc_ == null) {
                continue;
            }
            Exiting exiting_ = getNet().getExiting(doc_);
            if (exiting_ != null) {
                getNet().getFrames().getCompoFactory().invokeNow(new Quitting(exiting_, getNet(), getSocket()));
                return;
            }
            getNet().getFrames().getCompoFactory().invokeNow(new LoopClient(getNet(),doc_, getSocket()));
        }
    }
}
