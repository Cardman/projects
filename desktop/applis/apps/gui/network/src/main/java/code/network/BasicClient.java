package code.network;

import code.gui.CustComponent;
import code.gui.ThreadInvoker;
import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;

/**Thread safe class*/
public final class BasicClient extends SendReceive {

    private final AbstractBufferedReader input;

    public BasicClient(AbstractSocket _socket, NetGroupFrame _window) {
        super(_socket,_window);
        input = _socket.getInput();
    }

    @Override
    public void run() {
        Exiting ex_ = null;
        boolean noLine_ = false;
        while (true) {
            //tourne toujours
            String input_ = input.readLine();
            if (input_ == null) {
                noLine_ = true;
                break;
            }
            //on peut traiter les "timeout"
            Object readObject_ = getNet().getObject(input_);
            if (readObject_ == null) {
                continue;
            }
            if (readObject_ instanceof Exiting) {
                ex_ = (Exiting) readObject_;
                break;
            }
            ThreadInvoker.invokeNow(getNet().getThreadFactory(),new LoopClient(getNet(), readObject_, getSocket()));
        }
        if (!noLine_) {
            CustComponent.invokeLater(new Quitting(ex_, getNet(), getClosSocket()));
        }
    }
}
