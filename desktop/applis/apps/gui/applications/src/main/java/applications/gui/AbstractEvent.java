package applications.gui;

import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.LanguageDialogButtons;
import code.gui.events.AbsActionListener;

public abstract class AbstractEvent implements AbsActionListener {
    private final WindowApps window;
    private final AbsButton mainButton;
    private final String appName;
    AbstractEvent(WindowApps _window, AbsButton _but, String _n) {
        window = _window;
        mainButton = _but;
        appName = _n;
    }

    @Override
    public void action() {
        LanguageDialogButtons.enable(getMainButton(),false);
//        if (getLock().get() > 0) {
//            return;
//        }
        if (GuiBaseUtil.tryToReopen(appName,window.getFrames())) {
//            getLock().incrementAndGet();
            return;
        }
        launch(window);
    }
    protected abstract void launch(WindowApps _window);
//    public AbstractAtomicInteger getLock() {
//        return lock;
//    }

    public AbsButton getMainButton() {
        return mainButton;
    }
}
