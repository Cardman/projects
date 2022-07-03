package code.network;



import code.gui.ConfirmDialog;
import code.gui.GuiConstants;
import code.gui.initialize.AbstractSocket;
import code.scripts.messages.gui.MessGuiNetGr;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

/**Thread safe class*/
public final class Quitting implements Runnable {

    private static final String QUITTING = "network.quitting";

    private static final String FOLDER = "resources_network/messages";

    private static final String USED_PORT_TITLE = "usedPortTitle";

    private static final String USED_PORT = "usedPort";

    private Exiting bye;

    private final NetGroupFrame window;

    private final AbstractSocket socket;

    private StringMap<String> messages;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public Quitting(Exiting _bye, NetGroupFrame _window, AbstractSocket _socket) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(FOLDER, _window.getLanguageKey(), QUITTING);
        String loadedResourcesMessages_ = MessGuiNetGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        bye = _bye;
        window = _window;
        socket = _socket;
    }

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public Quitting(NetGroupFrame _window, AbstractSocket _socket) {
        window = _window;
        socket = _socket;
    }

    @Override
    public void run() {
        String lg_ = window.getLanguageKey();
        if (bye != null && bye.isBusy()) {
            String title_ = messages.getVal(USED_PORT_TITLE);
            String message_ = messages.getVal(USED_PORT);
            message_ = StringUtil.simpleNumberFormat(message_, window.getPort());
            window.getFrames().getMessageDialogAbs().input(window.getCommonFrame(), message_, title_, lg_, GuiConstants.ERROR_MESSAGE);
        }
        window.quitNetwork(bye, socket);
    }
}
