package code.expressionlanguage.guicompos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ListenerLaunchApp implements ActionListener {
    private MainWindow mainWindow;

    public ListenerLaunchApp(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.process();
    }
}
