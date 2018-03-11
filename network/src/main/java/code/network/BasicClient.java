package code.network;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.SwingUtilities;

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

        BufferedReader in_ = null;
        try {
            in_ = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            String input_;



            while (true) {
                //tourne toujours
                input_ = in_.readLine();
                if (input_ == null) {
                    break;
                }
                //on peut traiter les "timeout"
                Object readObject_ = window.getObject(input_);
                if (readObject_ == null) {
                    continue;
                }
                if (readObject_ instanceof Exiting) {
                    Exiting bye_ = (Exiting) readObject_;
                    SwingUtilities.invokeLater(new Quitting(bye_, window, getSocket()));
                    break;
                }
                ThreadInvoker.invokeNow(new LoopClient(window, readObject_, getSocket()));
            }
        } catch (Throwable _0) {
            SwingUtilities.invokeLater(new Quitting(window, getSocket()));
        }
    }
}
