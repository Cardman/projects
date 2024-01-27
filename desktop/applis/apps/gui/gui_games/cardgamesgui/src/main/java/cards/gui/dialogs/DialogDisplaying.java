package cards.gui.dialogs;

import cards.gui.WindowCardsInt;

public interface DialogDisplaying {

    void validateDisplaying();

    DialogDisplayingContent getDialogDisplayingContent();
    void savePrefs(WindowCardsInt _w);
}
