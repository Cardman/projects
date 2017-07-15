package cards.main;
import code.util.StringMap;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LaunchingGame extends Thread {

    private StringMap<Object> args;

    private String language;

    private LaunchingCards mainApplication;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LaunchingGame(LaunchingCards _mainApplication, StringMap<Object> _args, String _language) {
        mainApplication = _mainApplication;
        args = _args;
        language = _language;
    }

    @Override
    public void run() {
        mainApplication.launch(language, args);
    }
}
