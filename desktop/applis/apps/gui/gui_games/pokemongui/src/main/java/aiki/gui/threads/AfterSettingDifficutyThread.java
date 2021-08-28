package aiki.gui.threads;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import code.gui.GuiConstants;

/**Thread safe class*/
public final class AfterSettingDifficutyThread implements Runnable {

    private WindowAiki window;

    private FacadeGame facade;

    public AfterSettingDifficutyThread(WindowAiki _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void run() {
        window.setTextArea(facade.getGame().getCommentGame().join(), GuiConstants.WARNING_MESSAGE);
    }
}
