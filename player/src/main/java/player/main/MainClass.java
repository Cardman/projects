package player.main;
import javax.swing.SwingUtilities;

import player.gui.MainWindow;

public class MainClass implements Runnable {

    private String[] args;

    public MainClass(String[] _args) {
        args = _args;
    }

    public static void main(String[] _args) {
        SwingUtilities.invokeLater(new MainClass(_args));
    }

    @Override
    public void run() {
        new MainWindow(args);
    }

}
