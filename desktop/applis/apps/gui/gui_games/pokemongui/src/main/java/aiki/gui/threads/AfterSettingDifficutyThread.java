package aiki.gui.threads;
import javax.swing.JOptionPane;

import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;

/**Thread safe class*/
public final class AfterSettingDifficutyThread implements Runnable {

    private MainWindow window;

    private FacadeGame facade;

    public AfterSettingDifficutyThread(MainWindow _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void run() {
        window.setTextArea(facade.getGame().getCommentGame().join(), JOptionPane.WARNING_MESSAGE);
    }
}
