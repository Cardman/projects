package applications.gui;

import code.gui.GroupFrame;
import code.renders.LaunchingRenders;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class RenderEvent extends MouseAdapter {

    private MainWindow window;
    RenderEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (LaunchingRenders.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingRenders.getMainWindowClass(), window.getFrames())) {
            LaunchingRenders.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingRenders l_;
        l_ = new LaunchingRenders(window.getFrames());
        l_.launch(lg_, window.getFrames());
    }
}
