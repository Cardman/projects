package applications.gui;

import code.converterimages.main.LaunchingConverter;
import code.gui.GroupFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class ConverterEvent extends MouseAdapter {

    private MainWindow window;
    ConverterEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (LaunchingConverter.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingConverter.getMainWindowClass(), window.getFrames())) {
            LaunchingConverter.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingConverter l_;
        l_ = new LaunchingConverter(window.getFrames());
        l_.launch(lg_, window.getFrames());
    }
}
