package code.network;
import java.net.Socket;

import javax.swing.JOptionPane;

import code.gui.ConfirmDialog;
import code.stream.ExtractFromFiles;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

/**Thread safe class*/
public final class Quitting extends Thread {

    private static final String QUITTING = "network.Quitting";

    private static final String FOLDER = "resources_network/messages";

    private static final String USED_PORT_TITLE = "usedPortTitle";

    private static final String USED_PORT = "usedPort";

    private Exiting bye;

    private NetGroupFrame window;

    private Socket socket;

    private StringMap<String> messages = ExtractFromFiles.getMessagesFromLocaleClass(FOLDER, Constants.getLanguage(), QUITTING);

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public Quitting(Exiting _bye, NetGroupFrame _window, Socket _socket) {
        bye = _bye;
        window = _window;
        socket = _socket;
    }

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public Quitting(NetGroupFrame _window, Socket _socket) {
        window = _window;
        socket = _socket;
    }

    @Override
    public void run() {
        if (bye.isBusy()) {
            String title_ = messages.getVal(USED_PORT_TITLE);
            String message_ = messages.getVal(USED_PORT);
            message_ = StringList.simpleFormat(message_, window.getPort());
            ConfirmDialog.showMessage(window, message_, title_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
        }
        window.quitNetwork(bye, socket);
    }
}
