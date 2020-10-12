package applications.gui;

import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.GroupFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class AppsEvent extends MouseAdapter {

    private MainWindow window;
    AppsEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (LaunchingFull.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingFull.getMainWindowClass(), window.getFrames())) {
            LaunchingFull.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingFull l_;
        l_ = new LaunchingFull();
        l_.launch(lg_, window.getFrames());
    }
}
