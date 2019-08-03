package player.main;
import javax.swing.SwingUtilities;

import code.util.consts.Constants;
import player.gui.MainWindow;

public class MainClass implements Runnable {

    private String[] args;

    public MainClass(String[] _args) {
        args = _args;
    }

    public static void main(String[] _args) {
        CustComponent.invokeLater(new MainClass(_args));
    }

    @Override
    public void run() {
        new MainWindow(Constants.getDefaultLanguage(),args);
    }

}
