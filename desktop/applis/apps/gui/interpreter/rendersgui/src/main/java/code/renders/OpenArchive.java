package code.renders;

import code.gui.AbsActionListener;

public final class OpenArchive implements AbsActionListener {

    private WindowRenders mainWindow;

    public OpenArchive(WindowRenders _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void action() {
        mainWindow.load();
    }
}
