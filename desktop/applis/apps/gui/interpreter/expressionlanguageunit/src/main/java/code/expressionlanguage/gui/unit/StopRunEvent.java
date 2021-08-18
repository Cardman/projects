package code.expressionlanguage.gui.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class StopRunEvent implements ActionListener {
    private WindowUnit mainWindow;

    public StopRunEvent(WindowUnit _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.stop();
    }
}