package applications.gui;

import code.expressionlanguage.guicompos.GuiFactroy;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;

import java.util.concurrent.atomic.AtomicInteger;

public final class AppsEvent extends AbstractEvent {

    private final GuiFactroy fact;

    AppsEvent(MainWindow _window, AtomicInteger _at, GuiFactroy _fact) {
        super(_window,_at);
        fact = _fact;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingFull.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingFull l_;
        l_ = new LaunchingFull(_window.getFrames(), fact);
        l_.launch(lg_);
    }
}
