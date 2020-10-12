package applications.gui;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.gui.GroupFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class AppUnitEvent extends MouseAdapter {

    private MainWindow window;
    AppUnitEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (LaunchingAppUnitTests.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingAppUnitTests.getMainWindowClass(), window.getFrames())) {
            LaunchingAppUnitTests.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingAppUnitTests l_;
        l_ = new LaunchingAppUnitTests(window.getFrames());
        l_.launch(lg_, window.getFrames());
    }
}
