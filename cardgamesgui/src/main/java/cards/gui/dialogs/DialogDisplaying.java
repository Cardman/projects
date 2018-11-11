package cards.gui.dialogs;

import cards.gui.MainWindow;

public interface DialogDisplaying {

    void validateDisplaying();

    void addSuit();

    void removeSuit(MainWindow _window);
}
