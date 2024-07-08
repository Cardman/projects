package applications.gui;

import code.gui.AbsButton;
import code.gui.LanguageDialogButtons;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

public abstract class AbstractEvent implements AbsActionListener {
    private final WindowApps window;
    private final AbsButton mainButton;
    AbstractEvent(WindowApps _window, AbsButton _but) {
        window = _window;
        mainButton = _but;
    }

    @Override
    public void action() {
        LanguageDialogButtons.enable(getMainButton(),false);
//        if (getLock().get() > 0) {
//            return;
//        }
        if (tryToReopen(window.getFrames())) {
//            getLock().incrementAndGet();
            return;
        }
        launch(window);
    }
    protected abstract boolean tryToReopen(AbstractProgramInfos _list);
    protected abstract void launch(WindowApps _window);
//    public AbstractAtomicInteger getLock() {
//        return lock;
//    }

    public AbsButton getMainButton() {
        return mainButton;
    }
}
