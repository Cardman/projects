package code.network;

import code.gui.FrameUtil;
import code.gui.ThreadInvoker;
import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;

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
            Object readObject_ = getNet().getObject(input_);
            if (readObject_ == null) {
                continue;
            }
            if (readObject_ instanceof Exiting) {
                Exiting ex_ = (Exiting) readObject_;
                FrameUtil.invokeLater(new Quitting(ex_, getNet(), getSocket()));
                return;
            }
            ThreadInvoker.invokeNow(getNet().getThreadFactory(),new LoopClient(getNet(), readObject_, getSocket()));
        }
    }
}
