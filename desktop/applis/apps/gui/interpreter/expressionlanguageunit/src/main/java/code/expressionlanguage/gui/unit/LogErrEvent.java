package code.expressionlanguage.gui.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class LogErrEvent implements ActionListener {
    private MainWindow mainWindow;

    public LogErrEvent(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.logErr();
    }
}