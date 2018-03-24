package minirts;

import javax.swing.SwingUtilities;

public final class MainClass extends Thread {

    public static void main(String[] _args) {
        SwingUtilities.invokeLater(new MainClass());
    }

    @Override
    public void run() {
        new MainWindow();
    }
}
