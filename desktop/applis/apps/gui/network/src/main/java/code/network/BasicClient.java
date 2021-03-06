package code.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import code.gui.CustComponent;
import code.gui.ThreadInvoker;

/**Thread safe class*/
public final class BasicClient extends SendReceive {

    private NetGroupFrame window;

    public BasicClient(Socket _socket, NetGroupFrame _window) {
        super(_socket);
        window = _window;
    }

    @Override
    public void run() {

        try {
            BufferedReader in_ = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));


            Exiting ex_ = null;
            boolean noLine_ = false;
            while (true) {
                //tourne toujours
                String input_ = in_.readLine();
                if (input_ == null) {
                    noLine_ = true;
                    break;
                }
                //on peut traiter les "timeout"
                Object readObject_ = window.getObject(input_);
                if (readObject_ == null) {
                    continue;
                }
                if (readObject_ instanceof Exiting) {
                    ex_ = (Exiting) readObject_;
                    break;
                }
                ThreadInvoker.invokeNow(new LoopClient(window, readObject_, getSocket()));
            }
            if (!noLine_) {
                CustComponent.invokeLater(new Quitting(ex_, window, getSocket()));
            }
        } catch (IOException e){
            CustComponent.invokeLater(new Quitting(null, window, getSocket()));
        }
    }
}
