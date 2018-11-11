package minirts;

import javax.swing.SwingUtilities;

import code.util.consts.Constants;

public final class MainClass extends Thread {

    public static void main(String[] _args) {
        SwingUtilities.invokeLater(new MainClass());
    }

    @Override
    public void run() {
        new MainWindow(Constants.getDefaultLanguage());
    }
}
