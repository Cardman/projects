package code.expressionlanguage.gui.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ListenerLaunchTests implements ActionListener {
    private MainWindow mainWindow;

    public ListenerLaunchTests(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.process();
    }
}
