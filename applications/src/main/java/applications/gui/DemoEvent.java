package applications.gui;

import code.gui.GroupFrame;
import code.minirts.LaunchingDemo;
import code.renders.LaunchingRenders;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class DemoEvent extends MouseAdapter {

    private MainWindow window;
    DemoEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (LaunchingDemo.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingDemo.getMainWindowClass())) {
            LaunchingDemo.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingDemo l_;
        l_ = new LaunchingDemo();
        l_.launch(lg_);
    }
}
