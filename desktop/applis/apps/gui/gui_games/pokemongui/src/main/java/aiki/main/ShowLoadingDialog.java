package aiki.main;

import aiki.gui.WindowAikiInt;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ShowLoadingDialog implements Runnable {

    private final WindowAikiInt window;

    public ShowLoadingDialog(WindowAikiInt _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.progressDial().getAbsDialog().setVisible(true);
    }
}
