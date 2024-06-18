package code.network;

import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;

/**Thread safe class*/
public abstract class BasicClientAbs extends SendReceive {

    protected BasicClientAbs(AbstractSocket _socket) {
        super(_socket);
    }

    @Override
    public void run() {
        AbstractBufferedReader inputSock_ = getSocket().getInput();
        while (true) {
            //tourne toujours
//            String input_ = inputSock_.readLine();
//            if (input_ == null) {
//                return;
//            }
            //on peut traiter les "timeout"
            String input_ = inputSock_.readLine();
            if (input_ == null || !iterate(getSocket(), input_)) {
                return;
            }
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
        }
    }

    protected abstract boolean iterate(AbstractSocket _socket, String _input);

}
